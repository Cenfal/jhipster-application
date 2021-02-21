import { Component, Vue, Inject } from 'vue-property-decorator';

import { IArticleLanguage } from '@/shared/model/article-language.model';
import ArticleLanguageService from './article-language.service';

@Component
export default class ArticleLanguageDetails extends Vue {
  @Inject('articleLanguageService') private articleLanguageService: () => ArticleLanguageService;
  public articleLanguage: IArticleLanguage = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleLanguageId) {
        vm.retrieveArticleLanguage(to.params.articleLanguageId);
      }
    });
  }

  public retrieveArticleLanguage(articleLanguageId) {
    this.articleLanguageService()
      .find(articleLanguageId)
      .then(res => {
        this.articleLanguage = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
