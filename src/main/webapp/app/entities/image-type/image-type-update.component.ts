import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IImageType, ImageType } from '@/shared/model/image-type.model';
import ImageTypeService from './image-type.service';

const validations: any = {
  imageType: {
    typeId: {},
    type: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class ImageTypeUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('imageTypeService') private imageTypeService: () => ImageTypeService;
  public imageType: IImageType = new ImageType();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.imageTypeId) {
        vm.retrieveImageType(to.params.imageTypeId);
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
    if (this.imageType.id) {
      this.imageTypeService()
        .update(this.imageType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.imageType.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.imageTypeService()
        .create(this.imageType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.imageType.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveImageType(imageTypeId): void {
    this.imageTypeService()
      .find(imageTypeId)
      .then(res => {
        this.imageType = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
