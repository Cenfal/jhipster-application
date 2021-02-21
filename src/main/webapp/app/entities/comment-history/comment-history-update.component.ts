import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { ICommentHistory, CommentHistory } from '@/shared/model/comment-history.model';
import CommentHistoryService from './comment-history.service';

const validations: any = {
  commentHistory: {
    commentId: {},
    updateTime: {},
  },
};

@Component({
  validations,
})
export default class CommentHistoryUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('commentHistoryService') private commentHistoryService: () => CommentHistoryService;
  public commentHistory: ICommentHistory = new CommentHistory();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.commentHistoryId) {
        vm.retrieveCommentHistory(to.params.commentHistoryId);
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
    if (this.commentHistory.id) {
      this.commentHistoryService()
        .update(this.commentHistory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.commentHistory.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.commentHistoryService()
        .create(this.commentHistory)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.commentHistory.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveCommentHistory(commentHistoryId): void {
    this.commentHistoryService()
      .find(commentHistoryId)
      .then(res => {
        this.commentHistory = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
