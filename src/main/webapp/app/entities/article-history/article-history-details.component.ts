import { Component, Vue, Inject } from 'vue-property-decorator';

import { IArticleHistory } from '@/shared/model/article-history.model';
import ArticleHistoryService from './article-history.service';

@Component
export default class ArticleHistoryDetails extends Vue {
  @Inject('articleHistoryService') private articleHistoryService: () => ArticleHistoryService;
  public articleHistory: IArticleHistory = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleHistoryId) {
        vm.retrieveArticleHistory(to.params.articleHistoryId);
      }
    });
  }

  public retrieveArticleHistory(articleHistoryId) {
    this.articleHistoryService()
      .find(articleHistoryId)
      .then(res => {
        this.articleHistory = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
