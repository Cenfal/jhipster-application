/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ImageTypeComponent from '@/entities/image-type/image-type.vue';
import ImageTypeClass from '@/entities/image-type/image-type.component';
import ImageTypeService from '@/entities/image-type/image-type.service';

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
  describe('ImageType Management Component', () => {
    let wrapper: Wrapper<ImageTypeClass>;
    let comp: ImageTypeClass;
    let imageTypeServiceStub: SinonStubbedInstance<ImageTypeService>;

    beforeEach(() => {
      imageTypeServiceStub = sinon.createStubInstance<ImageTypeService>(ImageTypeService);
      imageTypeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ImageTypeClass>(ImageTypeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          imageTypeService: () => imageTypeServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      imageTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllImageTypes();
      await comp.$nextTick();

      // THEN
      expect(imageTypeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.imageTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      imageTypeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeImageType();
      await comp.$nextTick();

      // THEN
      expect(imageTypeServiceStub.delete.called).toBeTruthy();
      expect(imageTypeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
