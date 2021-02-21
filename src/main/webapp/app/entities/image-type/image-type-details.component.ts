import { Component, Vue, Inject } from 'vue-property-decorator';

import { IImageType } from '@/shared/model/image-type.model';
import ImageTypeService from './image-type.service';

@Component
export default class ImageTypeDetails extends Vue {
  @Inject('imageTypeService') private imageTypeService: () => ImageTypeService;
  public imageType: IImageType = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.imageTypeId) {
        vm.retrieveImageType(to.params.imageTypeId);
      }
    });
  }

  public retrieveImageType(imageTypeId) {
    this.imageTypeService()
      .find(imageTypeId)
      .then(res => {
        this.imageType = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
