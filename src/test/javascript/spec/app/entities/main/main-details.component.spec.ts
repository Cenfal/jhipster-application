/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MainDetailComponent from '@/entities/main/main-details.vue';
import MainClass from '@/entities/main/main-details.component';
import MainService from '@/entities/main/main.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Main Management Detail Component', () => {
    let wrapper: Wrapper<MainClass>;
    let comp: MainClass;
    let mainServiceStub: SinonStubbedInstance<MainService>;

    beforeEach(() => {
      mainServiceStub = sinon.createStubInstance<MainService>(MainService);

      wrapper = shallowMount<MainClass>(MainDetailComponent, { store, i18n, localVue, provide: { mainService: () => mainServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMain = { id: 123 };
        mainServiceStub.find.resolves(foundMain);

        // WHEN
        comp.retrieveMain(123);
        await comp.$nextTick();

        // THEN
        expect(comp.main).toBe(foundMain);
      });
    });
  });
});
