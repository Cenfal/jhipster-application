/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import StatusDetailComponent from '@/entities/status/status-details.vue';
import StatusClass from '@/entities/status/status-details.component';
import StatusService from '@/entities/status/status.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Status Management Detail Component', () => {
    let wrapper: Wrapper<StatusClass>;
    let comp: StatusClass;
    let statusServiceStub: SinonStubbedInstance<StatusService>;

    beforeEach(() => {
      statusServiceStub = sinon.createStubInstance<StatusService>(StatusService);

      wrapper = shallowMount<StatusClass>(StatusDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { statusService: () => statusServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundStatus = { id: 123 };
        statusServiceStub.find.resolves(foundStatus);

        // WHEN
        comp.retrieveStatus(123);
        await comp.$nextTick();

        // THEN
        expect(comp.status).toBe(foundStatus);
      });
    });
  });
});
