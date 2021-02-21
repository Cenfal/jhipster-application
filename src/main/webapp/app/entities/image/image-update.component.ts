import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IImage, Image } from '@/shared/model/image.model';
import ImageService from './image.service';

const validations: any = {
  image: {
    articleId: {},
    path: {},
    imageType: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class ImageUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('imageService') private imageService: () => ImageService;
  public image: IImage = new Image();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.imageId) {
        vm.retrieveImage(to.params.imageId);
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
    if (this.image.id) {
      this.imageService()
        .update(this.image)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.image.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.imageService()
        .create(this.image)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.image.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveImage(imageId): void {
    this.imageService()
      .find(imageId)
      .then(res => {
        this.image = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
