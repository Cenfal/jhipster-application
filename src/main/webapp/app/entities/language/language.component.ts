import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILanguage } from '@/shared/model/language.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import LanguageService from './language.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Language extends mixins(AlertMixin) {
  @Inject('languageService') private languageService: () => LanguageService;
  private removeId: number = null;

  public languages: ILanguage[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLanguages();
  }

  public clear(): void {
    this.retrieveAllLanguages();
  }

  public retrieveAllLanguages(): void {
    this.isFetching = true;

    this.languageService()
      .retrieve()
      .then(
        res => {
          this.languages = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ILanguage): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLanguage(): void {
    this.languageService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.language.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllLanguages();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
