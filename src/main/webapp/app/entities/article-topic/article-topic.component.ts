import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IArticleTopic } from '@/shared/model/article-topic.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ArticleTopicService from './article-topic.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ArticleTopic extends mixins(AlertMixin) {
  @Inject('articleTopicService') private articleTopicService: () => ArticleTopicService;
  private removeId: number = null;

  public articleTopics: IArticleTopic[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllArticleTopics();
  }

  public clear(): void {
    this.retrieveAllArticleTopics();
  }

  public retrieveAllArticleTopics(): void {
    this.isFetching = true;

    this.articleTopicService()
      .retrieve()
      .then(
        res => {
          this.articleTopics = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IArticleTopic): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeArticleTopic(): void {
    this.articleTopicService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.articleTopic.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllArticleTopics();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
