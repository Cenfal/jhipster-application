import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IArticleTopic, ArticleTopic } from '@/shared/model/article-topic.model';
import ArticleTopicService from './article-topic.service';

const validations: any = {
  articleTopic: {
    articleId: {},
    topicId: {},
  },
};

@Component({
  validations,
})
export default class ArticleTopicUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('articleTopicService') private articleTopicService: () => ArticleTopicService;
  public articleTopic: IArticleTopic = new ArticleTopic();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.articleTopicId) {
        vm.retrieveArticleTopic(to.params.articleTopicId);
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
    if (this.articleTopic.id) {
      this.articleTopicService()
        .update(this.articleTopic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleTopic.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.articleTopicService()
        .create(this.articleTopic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.articleTopic.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveArticleTopic(articleTopicId): void {
    this.articleTopicService()
      .find(articleTopicId)
      .then(res => {
        this.articleTopic = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
