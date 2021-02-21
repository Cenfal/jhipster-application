import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IJob } from '@/shared/model/job.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import JobService from './job.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Job extends mixins(AlertMixin) {
  @Inject('jobService') private jobService: () => JobService;
  private removeId: number = null;

  public jobs: IJob[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllJobs();
  }

  public clear(): void {
    this.retrieveAllJobs();
  }

  public retrieveAllJobs(): void {
    this.isFetching = true;

    this.jobService()
      .retrieve()
      .then(
        res => {
          this.jobs = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IJob): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeJob(): void {
    this.jobService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('jhipsterApplicationApp.job.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllJobs();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
