/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MainUpdateComponent from '@/entities/main/main-update.vue';
import MainClass from '@/entities/main/main-update.component';
import MainService from '@/entities/main/main.service';

import ContactService from '@/entities/contact/contact.service';

import UserWebsiteService from '@/entities/user-website/user-website.service';

import ProfilePhotoService from '@/entities/profile-photo/profile-photo.service';

import ImageService from '@/entities/image/image.service';

import ArticleHistoryService from '@/entities/article-history/article-history.service';

import CommentHistoryService from '@/entities/comment-history/comment-history.service';

import ArticleService from '@/entities/article/article.service';

import CommentService from '@/entities/comment/comment.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Main Management Update Component', () => {
    let wrapper: Wrapper<MainClass>;
    let comp: MainClass;
    let mainServiceStub: SinonStubbedInstance<MainService>;

    beforeEach(() => {
      mainServiceStub = sinon.createStubInstance<MainService>(MainService);

      wrapper = shallowMount<MainClass>(MainUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          mainService: () => mainServiceStub,

          contactService: () => new ContactService(),

          userWebsiteService: () => new UserWebsiteService(),

          profilePhotoService: () => new ProfilePhotoService(),

          imageService: () => new ImageService(),

          articleHistoryService: () => new ArticleHistoryService(),

          commentHistoryService: () => new CommentHistoryService(),

          articleService: () => new ArticleService(),

          commentService: () => new CommentService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.main = entity;
        mainServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mainServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.main = entity;
        mainServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mainServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
