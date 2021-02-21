/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ImageTypeUpdateComponent from '@/entities/image-type/image-type-update.vue';
import ImageTypeClass from '@/entities/image-type/image-type-update.component';
import ImageTypeService from '@/entities/image-type/image-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ImageType Management Update Component', () => {
    let wrapper: Wrapper<ImageTypeClass>;
    let comp: ImageTypeClass;
    let imageTypeServiceStub: SinonStubbedInstance<ImageTypeService>;

    beforeEach(() => {
      imageTypeServiceStub = sinon.createStubInstance<ImageTypeService>(ImageTypeService);

      wrapper = shallowMount<ImageTypeClass>(ImageTypeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          imageTypeService: () => imageTypeServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.imageType = entity;
        imageTypeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(imageTypeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.imageType = entity;
        imageTypeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(imageTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
