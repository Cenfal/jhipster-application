import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import ContactService from '../contact/contact.service';
import { IContact } from '@/shared/model/contact.model';

import UserWebsiteService from '../user-website/user-website.service';
import { IUserWebsite } from '@/shared/model/user-website.model';

import ProfilePhotoService from '../profile-photo/profile-photo.service';
import { IProfilePhoto } from '@/shared/model/profile-photo.model';

import ImageService from '../image/image.service';
import { IImage } from '@/shared/model/image.model';

import ArticleHistoryService from '../article-history/article-history.service';
import { IArticleHistory } from '@/shared/model/article-history.model';

import CommentHistoryService from '../comment-history/comment-history.service';
import { ICommentHistory } from '@/shared/model/comment-history.model';

import ArticleService from '../article/article.service';
import { IArticle } from '@/shared/model/article.model';

import CommentService from '../comment/comment.service';
import { IComment } from '@/shared/model/comment.model';

import AlertService from '@/shared/alert/alert.service';
import { IMain, Main } from '@/shared/model/main.model';
import MainService from './main.service';

const validations: any = {
  main: {
    createDate: {},
    updateDate: {},
    endDate: {},
    createdBy: {},
    updatedBy: {},
  },
};

@Component({
  validations,
})
export default class MainUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('mainService') private mainService: () => MainService;
  public main: IMain = new Main();

  @Inject('contactService') private contactService: () => ContactService;

  public contacts: IContact[] = [];

  @Inject('userWebsiteService') private userWebsiteService: () => UserWebsiteService;

  public userWebsites: IUserWebsite[] = [];

  @Inject('profilePhotoService') private profilePhotoService: () => ProfilePhotoService;

  public profilePhotos: IProfilePhoto[] = [];

  @Inject('imageService') private imageService: () => ImageService;

  public images: IImage[] = [];

  @Inject('articleHistoryService') private articleHistoryService: () => ArticleHistoryService;

  public articleHistories: IArticleHistory[] = [];

  @Inject('commentHistoryService') private commentHistoryService: () => CommentHistoryService;

  public commentHistories: ICommentHistory[] = [];

  @Inject('articleService') private articleService: () => ArticleService;

  public articles: IArticle[] = [];

  @Inject('commentService') private commentService: () => CommentService;

  public comments: IComment[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.mainId) {
        vm.retrieveMain(to.params.mainId);
      }
      vm.initRelationships();
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
    if (this.main.id) {
      this.mainService()
        .update(this.main)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.main.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.mainService()
        .create(this.main)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.main.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveMain(mainId): void {
    this.mainService()
      .find(mainId)
      .then(res => {
        this.main = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.contactService()
      .retrieve()
      .then(res => {
        this.contacts = res.data;
      });
    this.userWebsiteService()
      .retrieve()
      .then(res => {
        this.userWebsites = res.data;
      });
    this.profilePhotoService()
      .retrieve()
      .then(res => {
        this.profilePhotos = res.data;
      });
    this.imageService()
      .retrieve()
      .then(res => {
        this.images = res.data;
      });
    this.articleHistoryService()
      .retrieve()
      .then(res => {
        this.articleHistories = res.data;
      });
    this.commentHistoryService()
      .retrieve()
      .then(res => {
        this.commentHistories = res.data;
      });
    this.articleService()
      .retrieve()
      .then(res => {
        this.articles = res.data;
      });
    this.commentService()
      .retrieve()
      .then(res => {
        this.comments = res.data;
      });
  }
}
