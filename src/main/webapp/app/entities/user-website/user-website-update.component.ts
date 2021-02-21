import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IUserWebsite, UserWebsite } from '@/shared/model/user-website.model';
import UserWebsiteService from './user-website.service';

const validations: any = {
  userWebsite: {
    code: {},
    name: {},
    surname: {},
    formattedName: {},
    userName: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class UserWebsiteUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('userWebsiteService') private userWebsiteService: () => UserWebsiteService;
  public userWebsite: IUserWebsite = new UserWebsite();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userWebsiteId) {
        vm.retrieveUserWebsite(to.params.userWebsiteId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.userWebsite.id) {
      this.userWebsiteService()
        .update(this.userWebsite)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.userWebsite.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.userWebsiteService()
        .create(this.userWebsite)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.userWebsite.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveUserWebsite(userWebsiteId): void {
    this.userWebsiteService()
      .find(userWebsiteId)
      .then(res => {
        this.userWebsite = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
