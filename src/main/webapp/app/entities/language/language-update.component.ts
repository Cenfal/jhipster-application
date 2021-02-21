import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { ILanguage, Language } from '@/shared/model/language.model';
import LanguageService from './language.service';

const validations: any = {
  language: {
    language: {},
  },
};

@Component({
  validations,
})
export default class LanguageUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('languageService') private languageService: () => LanguageService;
  public language: ILanguage = new Language();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.languageId) {
        vm.retrieveLanguage(to.params.languageId);
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
    if (this.language.id) {
      this.languageService()
        .update(this.language)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.language.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.languageService()
        .create(this.language)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.language.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveLanguage(languageId): void {
    this.languageService()
      .find(languageId)
      .then(res => {
        this.language = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
