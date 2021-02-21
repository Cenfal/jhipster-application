/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ImageDetailComponent from '@/entities/image/image-details.vue';
import ImageClass from '@/entities/image/image-details.component';
import ImageService from '@/entities/image/image.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Image Management Detail Component', () => {
    let wrapper: Wrapper<ImageClass>;
    let comp: ImageClass;
    let imageServiceStub: SinonStubbedInstance<ImageService>;

    beforeEach(() => {
      imageServiceStub = sinon.createStubInstance<ImageService>(ImageService);

      wrapper = shallowMount<ImageClass>(ImageDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { imageService: () => imageServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundImage = { id: 123 };
        imageServiceStub.find.resolves(foundImage);

        // WHEN
        comp.retrieveImage(123);
        await comp.$nextTick();

        // THEN
        expect(comp.image).toBe(foundImage);
      });
    });
  });
});
