import { Component, Vue, Inject } from 'vue-property-decorator';

import { IArticleTag } from '@/shared/model/article-tag.model';
import ArticleTagService from './article-tag.service';

@Component
export default class ArticleTagDetails extends Vue {
  @Inject('articleTagService') private articleTagService: () => ArticleTagService;
  public articleTag: IArticleTag = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleTagId) {
        vm.retrieveArticleTag(to.params.articleTagId);
      }
    });
  }

  public retrieveArticleTag(articleTagId) {
    this.articleTagService()
      .find(articleTagId)
      .then(res => {
        this.articleTag = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
