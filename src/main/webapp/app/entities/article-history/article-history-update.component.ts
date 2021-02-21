import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IArticleHistory, ArticleHistory } from '@/shared/model/article-history.model';
import ArticleHistoryService from './article-history.service';

const validations: any = {
  articleHistory: {
    articleId: {},
    updateTime: {},
  },
};

@Component({
  validations,
})
export default class ArticleHistoryUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('articleHistoryService') private articleHistoryService: () => ArticleHistoryService;
  public articleHistory: IArticleHistory = new ArticleHistory();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleHistoryId) {
        vm.retrieveArticleHistory(to.params.articleHistoryId);
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
    if (this.articleHistory.id) {
      this.articleHistoryService()
        .update(this.articleHistory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleHistory.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.articleHistoryService()
        .create(this.articleHistory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleHistory.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveArticleHistory(articleHistoryId): void {
    this.articleHistoryService()
      .find(articleHistoryId)
      .then(res => {
        this.articleHistory = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
