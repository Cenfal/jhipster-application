/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ImageTypeDetailComponent from '@/entities/image-type/image-type-details.vue';
import ImageTypeClass from '@/entities/image-type/image-type-details.component';
import ImageTypeService from '@/entities/image-type/image-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ImageType Management Detail Component', () => {
    let wrapper: Wrapper<ImageTypeClass>;
    let comp: ImageTypeClass;
    let imageTypeServiceStub: SinonStubbedInstance<ImageTypeService>;

    beforeEach(() => {
      imageTypeServiceStub = sinon.createStubInstance<ImageTypeService>(ImageTypeService);

      wrapper = shallowMount<ImageTypeClass>(ImageTypeDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { imageTypeService: () => imageTypeServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundImageType = { id: 123 };
        imageTypeServiceStub.find.resolves(foundImageType);

        // WHEN
        comp.retrieveImageType(123);
        await comp.$nextTick();

        // THEN
        expect(comp.imageType).toBe(foundImageType);
      });
    });
  });
});
