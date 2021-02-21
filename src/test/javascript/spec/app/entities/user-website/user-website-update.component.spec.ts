/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UserWebsiteUpdateComponent from '@/entities/user-website/user-website-update.vue';
import UserWebsiteClass from '@/entities/user-website/user-website-update.component';
import UserWebsiteService from '@/entities/user-website/user-website.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('UserWebsite Management Update Component', () => {
    let wrapper: Wrapper<UserWebsiteClass>;
    let comp: UserWebsiteClass;
    let userWebsiteServiceStub: SinonStubbedInstance<UserWebsiteService>;

    beforeEach(() => {
      userWebsiteServiceStub = sinon.createStubInstance<UserWebsiteService>(UserWebsiteService);

      wrapper = shallowMount<UserWebsiteClass>(UserWebsiteUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          userWebsiteService: () => userWebsiteServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.userWebsite = entity;
        userWebsiteServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userWebsiteServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.userWebsite = entity;
        userWebsiteServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userWebsiteServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
