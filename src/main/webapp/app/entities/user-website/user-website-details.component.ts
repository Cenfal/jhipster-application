import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUserWebsite } from '@/shared/model/user-website.model';
import UserWebsiteService from './user-website.service';

@Component
export default class UserWebsiteDetails extends Vue {
  @Inject('userWebsiteService') private userWebsiteService: () => UserWebsiteService;
  public userWebsite: IUserWebsite = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userWebsiteId) {
        vm.retrieveUserWebsite(to.params.userWebsiteId);
      }
    });
  }

  public retrieveUserWebsite(userWebsiteId) {
    this.userWebsiteService()
      .find(userWebsiteId)
      .then(res => {
        this.userWebsite = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
