import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IStatus, Status } from '@/shared/model/status.model';
import StatusService from './status.service';

const validations: any = {
  status: {
    value: {},
  },
};

@Component({
  validations,
})
export default class StatusUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('statusService') private statusService: () => StatusService;
  public status: IStatus = new Status();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.statusId) {
        vm.retrieveStatus(to.params.statusId);
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
    if (this.status.id) {
      this.statusService()
        .update(this.status)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.status.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.statusService()
        .create(this.status)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.status.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveStatus(statusId): void {
    this.statusService()
      .find(statusId)
      .then(res => {
        this.status = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
