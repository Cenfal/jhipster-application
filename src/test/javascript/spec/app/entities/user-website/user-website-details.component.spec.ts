/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UserWebsiteDetailComponent from '@/entities/user-website/user-website-details.vue';
import UserWebsiteClass from '@/entities/user-website/user-website-details.component';
import UserWebsiteService from '@/entities/user-website/user-website.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('UserWebsite Management Detail Component', () => {
    let wrapper: Wrapper<UserWebsiteClass>;
    let comp: UserWebsiteClass;
    let userWebsiteServiceStub: SinonStubbedInstance<UserWebsiteService>;

    beforeEach(() => {
      userWebsiteServiceStub = sinon.createStubInstance<UserWebsiteService>(UserWebsiteService);

      wrapper = shallowMount<UserWebsiteClass>(UserWebsiteDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { userWebsiteService: () => userWebsiteServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUserWebsite = { id: 123 };
        userWebsiteServiceStub.find.resolves(foundUserWebsite);

        // WHEN
        comp.retrieveUserWebsite(123);
        await comp.$nextTick();

        // THEN
        expect(comp.userWebsite).toBe(foundUserWebsite);
      });
    });
  });
});
