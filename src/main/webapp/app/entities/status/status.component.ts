import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IStatus } from '@/shared/model/status.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import StatusService from './status.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Status extends mixins(AlertMixin) {
  @Inject('statusService') private statusService: () => StatusService;
  private removeId: number = null;

  public statuses: IStatus[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllStatuss();
  }

  public clear(): void {
    this.retrieveAllStatuss();
  }

  public retrieveAllStatuss(): void {
    this.isFetching = true;

    this.statusService()
      .retrieve()
      .then(
        res => {
          this.statuses = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IStatus): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeStatus(): void {
    this.statusService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.status.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllStatuss();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
