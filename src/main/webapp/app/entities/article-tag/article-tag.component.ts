import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IArticleTag } from '@/shared/model/article-tag.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ArticleTagService from './article-tag.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ArticleTag extends mixins(AlertMixin) {
  @Inject('articleTagService') private articleTagService: () => ArticleTagService;
  private removeId: number = null;

  public articleTags: IArticleTag[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllArticleTags();
  }

  public clear(): void {
    this.retrieveAllArticleTags();
  }

  public retrieveAllArticleTags(): void {
    this.isFetching = true;

    this.articleTagService()
      .retrieve()
      .then(
        res => {
          this.articleTags = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IArticleTag): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeArticleTag(): void {
    this.articleTagService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.articleTag.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllArticleTags();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
