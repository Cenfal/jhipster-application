import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IArticleLanguage, ArticleLanguage } from '@/shared/model/article-language.model';
import ArticleLanguageService from './article-language.service';

const validations: any = {
  articleLanguage: {
    articleId: {},
    languageId: {},
  },
};

@Component({
  validations,
})
export default class ArticleLanguageUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('articleLanguageService') private articleLanguageService: () => ArticleLanguageService;
  public articleLanguage: IArticleLanguage = new ArticleLanguage();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleLanguageId) {
        vm.retrieveArticleLanguage(to.params.articleLanguageId);
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
    if (this.articleLanguage.id) {
      this.articleLanguageService()
        .update(this.articleLanguage)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleLanguage.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.articleLanguageService()
        .create(this.articleLanguage)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleLanguage.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveArticleLanguage(articleLanguageId): void {
    this.articleLanguageService()
      .find(articleLanguageId)
      .then(res => {
        this.articleLanguage = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
