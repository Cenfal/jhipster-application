import { Component, Vue, Inject } from 'vue-property-decorator';

import { IArticleTopic } from '@/shared/model/article-topic.model';
import ArticleTopicService from './article-topic.service';

@Component
export default class ArticleTopicDetails extends Vue {
  @Inject('articleTopicService') private articleTopicService: () => ArticleTopicService;
  public articleTopic: IArticleTopic = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleTopicId) {
        vm.retrieveArticleTopic(to.params.articleTopicId);
      }
    });
  }

  public retrieveArticleTopic(articleTopicId) {
    this.articleTopicService()
      .find(articleTopicId)
      .then(res => {
        this.articleTopic = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
