/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleHistoryUpdateComponent from '@/entities/article-history/article-history-update.vue';
import ArticleHistoryClass from '@/entities/article-history/article-history-update.component';
import ArticleHistoryService from '@/entities/article-history/article-history.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ArticleHistory Management Update Component', () => {
    let wrapper: Wrapper<ArticleHistoryClass>;
    let comp: ArticleHistoryClass;
    let articleHistoryServiceStub: SinonStubbedInstance<ArticleHistoryService>;

    beforeEach(() => {
      articleHistoryServiceStub = sinon.createStubInstance<ArticleHistoryService>(ArticleHistoryService);

      wrapper = shallowMount<ArticleHistoryClass>(ArticleHistoryUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          articleHistoryService: () => articleHistoryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.articleHistory = entity;
        articleHistoryServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleHistoryServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.articleHistory = entity;
        articleHistoryServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleHistoryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
