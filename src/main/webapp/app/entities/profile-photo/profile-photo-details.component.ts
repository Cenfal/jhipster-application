import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProfilePhoto } from '@/shared/model/profile-photo.model';
import ProfilePhotoService from './profile-photo.service';

@Component
export default class ProfilePhotoDetails extends Vue {
  @Inject('profilePhotoService') private profilePhotoService: () => ProfilePhotoService;
  public profilePhoto: IProfilePhoto = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.profilePhotoId) {
        vm.retrieveProfilePhoto(to.params.profilePhotoId);
      }
    });
  }

  public retrieveProfilePhoto(profilePhotoId) {
    this.profilePhotoService()
      .find(profilePhotoId)
      .then(res => {
        this.profilePhoto = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
