import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IProfilePhoto, ProfilePhoto } from '@/shared/model/profile-photo.model';
import ProfilePhotoService from './profile-photo.service';

const validations: any = {
  profilePhoto: {
    userId: {},
    path: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class ProfilePhotoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('profilePhotoService') private profilePhotoService: () => ProfilePhotoService;
  public profilePhoto: IProfilePhoto = new ProfilePhoto();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.profilePhotoId) {
        vm.retrieveProfilePhoto(to.params.profilePhotoId);
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
    if (this.profilePhoto.id) {
      this.profilePhotoService()
        .update(this.profilePhoto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.profilePhoto.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.profilePhotoService()
        .create(this.profilePhoto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.profilePhoto.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveProfilePhoto(profilePhotoId): void {
    this.profilePhotoService()
      .find(profilePhotoId)
      .then(res => {
        this.profilePhoto = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
