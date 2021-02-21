import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IComment, Comment } from '@/shared/model/comment.model';
import CommentService from './comment.service';

const validations: any = {
  comment: {
    comment: {},
    userID: {},
    articleId: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class CommentUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('commentService') private commentService: () => CommentService;
  public comment: IComment = new Comment();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.commentId) {
        vm.retrieveComment(to.params.commentId);
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
    if (this.comment.id) {
      this.commentService()
        .update(this.comment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.comment.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.commentService()
        .create(this.comment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.comment.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveComment(commentId): void {
    this.commentService()
      .find(commentId)
      .then(res => {
        this.comment = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
