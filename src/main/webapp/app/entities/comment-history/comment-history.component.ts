import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICommentHistory } from '@/shared/model/comment-history.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import CommentHistoryService from './comment-history.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class CommentHistory extends mixins(AlertMixin) {
  @Inject('commentHistoryService') private commentHistoryService: () => CommentHistoryService;
  private removeId: number = null;

  public commentHistories: ICommentHistory[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCommentHistorys();
  }

  public clear(): void {
    this.retrieveAllCommentHistorys();
  }

  public retrieveAllCommentHistorys(): void {
    this.isFetching = true;

    this.commentHistoryService()
      .retrieve()
      .then(
        res => {
          this.commentHistories = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ICommentHistory): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCommentHistory(): void {
    this.commentHistoryService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.commentHistory.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllCommentHistorys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
