import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IArticle, Article } from '@/shared/model/article.model';
import ArticleService from './article.service';

const validations: any = {
  article: {
    code: {},
    title: {},
    text: {},
    userId: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class ArticleUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('articleService') private articleService: () => ArticleService;
  public article: IArticle = new Article();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleId) {
        vm.retrieveArticle(to.params.articleId);
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
    if (this.article.id) {
      this.articleService()
        .update(this.article)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.article.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.articleService()
        .create(this.article)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.article.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveArticle(articleId): void {
    this.articleService()
      .find(articleId)
      .then(res => {
        this.article = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
