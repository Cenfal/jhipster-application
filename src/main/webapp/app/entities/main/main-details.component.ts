import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMain } from '@/shared/model/main.model';
import MainService from './main.service';

@Component
export default class MainDetails extends Vue {
  @Inject('mainService') private mainService: () => MainService;
  public main: IMain = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.mainId) {
        vm.retrieveMain(to.params.mainId);
      }
    });
  }

  public retrieveMain(mainId) {
    this.mainService()
      .find(mainId)
      .then(res => {
        this.main = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
