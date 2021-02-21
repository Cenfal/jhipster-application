import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IArticleLanguage } from '@/shared/model/article-language.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ArticleLanguageService from './article-language.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ArticleLanguage extends mixins(AlertMixin) {
  @Inject('articleLanguageService') private articleLanguageService: () => ArticleLanguageService;
  private removeId: number = null;

  public articleLanguages: IArticleLanguage[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllArticleLanguages();
  }

  public clear(): void {
    this.retrieveAllArticleLanguages();
  }

  public retrieveAllArticleLanguages(): void {
    this.isFetching = true;

    this.articleLanguageService()
      .retrieve()
      .then(
        res => {
          this.articleLanguages = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IArticleLanguage): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeArticleLanguage(): void {
    this.articleLanguageService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.articleLanguage.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllArticleLanguages();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
