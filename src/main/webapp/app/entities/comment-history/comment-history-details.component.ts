import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICommentHistory } from '@/shared/model/comment-history.model';
import CommentHistoryService from './comment-history.service';

@Component
export default class CommentHistoryDetails extends Vue {
  @Inject('commentHistoryService') private commentHistoryService: () => CommentHistoryService;
  public commentHistory: ICommentHistory = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.commentHistoryId) {
        vm.retrieveCommentHistory(to.params.commentHistoryId);
      }
    });
  }

  public retrieveCommentHistory(commentHistoryId) {
    this.commentHistoryService()
      .find(commentHistoryId)
      .then(res => {
        this.commentHistory = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
