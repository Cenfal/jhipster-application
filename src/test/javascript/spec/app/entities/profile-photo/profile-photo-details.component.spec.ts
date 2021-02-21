/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ProfilePhotoDetailComponent from '@/entities/profile-photo/profile-photo-details.vue';
import ProfilePhotoClass from '@/entities/profile-photo/profile-photo-details.component';
import ProfilePhotoService from '@/entities/profile-photo/profile-photo.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ProfilePhoto Management Detail Component', () => {
    let wrapper: Wrapper<ProfilePhotoClass>;
    let comp: ProfilePhotoClass;
    let profilePhotoServiceStub: SinonStubbedInstance<ProfilePhotoService>;

    beforeEach(() => {
      profilePhotoServiceStub = sinon.createStubInstance<ProfilePhotoService>(ProfilePhotoService);

      wrapper = shallowMount<ProfilePhotoClass>(ProfilePhotoDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { profilePhotoService: () => profilePhotoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundProfilePhoto = { id: 123 };
        profilePhotoServiceStub.find.resolves(foundProfilePhoto);

        // WHEN
        comp.retrieveProfilePhoto(123);
        await comp.$nextTick();

        // THEN
        expect(comp.profilePhoto).toBe(foundProfilePhoto);
      });
    });
  });
});
