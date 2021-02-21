import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IImageType } from '@/shared/model/image-type.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ImageTypeService from './image-type.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ImageType extends mixins(AlertMixin) {
  @Inject('imageTypeService') private imageTypeService: () => ImageTypeService;
  private removeId: number = null;

  public imageTypes: IImageType[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllImageTypes();
  }

  public clear(): void {
    this.retrieveAllImageTypes();
  }

  public retrieveAllImageTypes(): void {
    this.isFetching = true;

    this.imageTypeService()
      .retrieve()
      .then(
        res => {
          this.imageTypes = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IImageType): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeImageType(): void {
    this.imageTypeService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.imageType.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllImageTypes();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
