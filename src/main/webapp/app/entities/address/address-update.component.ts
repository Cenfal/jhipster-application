import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IAddress, Address } from '@/shared/model/address.model';
import AddressService from './address.service';

const validations: any = {
  address: {
    countryCode: {},
    country: {},
    cityCode: {},
    city: {},
    districtCode: {},
    district: {},
    countyCode: {},
    county: {},
    neighborhoodCode: {},
    neighborhood: {},
    shipmentCode: {},
    shipment: {},
    villageCode: {},
    village: {},
    streetCode: {},
    street: {},
    buildingCode: {},
    buildingNumber: {},
    building: {},
    buildingFree: {},
    independentPartsCode: {},
    independentParts: {},
    independentPartsFree: {},
    postalCode: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class AddressUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('addressService') private addressService: () => AddressService;
  public address: IAddress = new Address();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.addressId) {
        vm.retrieveAddress(to.params.addressId);
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
    if (this.address.id) {
      this.addressService()
        .update(this.address)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.address.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.addressService()
        .create(this.address)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.address.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveAddress(addressId): void {
    this.addressService()
      .find(addressId)
      .then(res => {
        this.address = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
