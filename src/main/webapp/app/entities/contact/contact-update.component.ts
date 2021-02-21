import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IContact, Contact } from '@/shared/model/contact.model';
import ContactService from './contact.service';

const validations: any = {
  contact: {
    userId: {},
    mail: {},
    phone: {},
    preferred: {},
    addressId: {},
    statusId: {},
  },
};

@Component({
  validations,
})
export default class ContactUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contactService') private contactService: () => ContactService;
  public contact: IContact = new Contact();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contactId) {
        vm.retrieveContact(to.params.contactId);
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
    if (this.contact.id) {
      this.contactService()
        .update(this.contact)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.contact.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.contactService()
        .create(this.contact)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('jhipsterApplicationApp.contact.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveContact(contactId): void {
    this.contactService()
      .find(contactId)
      .then(res => {
        this.contact = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
