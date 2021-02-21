/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ProfilePhotoUpdateComponent from '@/entities/profile-photo/profile-photo-update.vue';
import ProfilePhotoClass from '@/entities/profile-photo/profile-photo-update.component';
import ProfilePhotoService from '@/entities/profile-photo/profile-photo.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ProfilePhoto Management Update Component', () => {
    let wrapper: Wrapper<ProfilePhotoClass>;
    let comp: ProfilePhotoClass;
    let profilePhotoServiceStub: SinonStubbedInstance<ProfilePhotoService>;

    beforeEach(() => {
      profilePhotoServiceStub = sinon.createStubInstance<ProfilePhotoService>(ProfilePhotoService);

      wrapper = shallowMount<ProfilePhotoClass>(ProfilePhotoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          profilePhotoService: () => profilePhotoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.profilePhoto = entity;
        profilePhotoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(profilePhotoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.profilePhoto = entity;
        profilePhotoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(profilePhotoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
