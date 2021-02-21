/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleLanguageUpdateComponent from '@/entities/article-language/article-language-update.vue';
import ArticleLanguageClass from '@/entities/article-language/article-language-update.component';
import ArticleLanguageService from '@/entities/article-language/article-language.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ArticleLanguage Management Update Component', () => {
    let wrapper: Wrapper<ArticleLanguageClass>;
    let comp: ArticleLanguageClass;
    let articleLanguageServiceStub: SinonStubbedInstance<ArticleLanguageService>;

    beforeEach(() => {
      articleLanguageServiceStub = sinon.createStubInstance<ArticleLanguageService>(ArticleLanguageService);

      wrapper = shallowMount<ArticleLanguageClass>(ArticleLanguageUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          articleLanguageService: () => articleLanguageServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.articleLanguage = entity;
        articleLanguageServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleLanguageServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.articleLanguage = entity;
        articleLanguageServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleLanguageServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
