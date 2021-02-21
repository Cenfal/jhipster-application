import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IArticleHistory } from '@/shared/model/article-history.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ArticleHistoryService from './article-history.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ArticleHistory extends mixins(AlertMixin) {
  @Inject('articleHistoryService') private articleHistoryService: () => ArticleHistoryService;
  private removeId: number = null;

  public articleHistories: IArticleHistory[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllArticleHistorys();
  }

  public clear(): void {
    this.retrieveAllArticleHistorys();
  }

  public retrieveAllArticleHistorys(): void {
    this.isFetching = true;

    this.articleHistoryService()
      .retrieve()
      .then(
        res => {
          this.articleHistories = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IArticleHistory): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeArticleHistory(): void {
    this.articleHistoryService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.articleHistory.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllArticleHistorys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
