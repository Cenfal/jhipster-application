import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IArticleTag, ArticleTag } from '@/shared/model/article-tag.model';
import ArticleTagService from './article-tag.service';

const validations: any = {
  articleTag: {
    articleId: {},
    tagId: {},
  },
};

@Component({
  validations,
})
export default class ArticleTagUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('articleTagService') private articleTagService: () => ArticleTagService;
  public articleTag: IArticleTag = new ArticleTag();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleTagId) {
        vm.retrieveArticleTag(to.params.articleTagId);
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
    if (this.articleTag.id) {
      this.articleTagService()
        .update(this.articleTag)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleTag.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.articleTagService()
        .create(this.articleTag)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleTag.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveArticleTag(articleTagId): void {
    this.articleTagService()
      .find(articleTagId)
      .then(res => {
        this.articleTag = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
