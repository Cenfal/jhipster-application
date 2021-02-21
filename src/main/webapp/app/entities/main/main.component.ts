import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IMain } from '@/shared/model/main.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import MainService from './main.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Main extends mixins(AlertMixin) {
  @Inject('mainService') private mainService: () => MainService;
  private removeId: number = null;

  public mains: IMain[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllMains();
  }

  public clear(): void {
    this.retrieveAllMains();
  }

  public retrieveAllMains(): void {
    this.isFetching = true;

    this.mainService()
      .retrieve()
      .then(
        res => {
          this.mains = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IMain): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeMain(): void {
    this.mainService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.main.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllMains();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
