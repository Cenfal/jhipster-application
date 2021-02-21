/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MainComponent from '@/entities/main/main.vue';
import MainClass from '@/entities/main/main.component';
import MainService from '@/entities/main/main.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Main Management Component', () => {
    let wrapper: Wrapper<MainClass>;
    let comp: MainClass;
    let mainServiceStub: SinonStubbedInstance<MainService>;

    beforeEach(() => {
      mainServiceStub = sinon.createStubInstance<MainService>(MainService);
      mainServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<MainClass>(MainComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          mainService: () => mainServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      mainServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllMains();
      await comp.$nextTick();

      // THEN
      expect(mainServiceStub.retrieve.called).toBeTruthy();
      expect(comp.mains[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      mainServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeMain();
      await comp.$nextTick();

      // THEN
      expect(mainServiceStub.delete.called).toBeTruthy();
      expect(mainServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
