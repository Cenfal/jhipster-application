/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleTagUpdateComponent from '@/entities/article-tag/article-tag-update.vue';
import ArticleTagClass from '@/entities/article-tag/article-tag-update.component';
import ArticleTagService from '@/entities/article-tag/article-tag.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ArticleTag Management Update Component', () => {
    let wrapper: Wrapper<ArticleTagClass>;
    let comp: ArticleTagClass;
    let articleTagServiceStub: SinonStubbedInstance<ArticleTagService>;

    beforeEach(() => {
      articleTagServiceStub = sinon.createStubInstance<ArticleTagService>(ArticleTagService);

      wrapper = shallowMount<ArticleTagClass>(ArticleTagUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          articleTagService: () => articleTagServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.articleTag = entity;
        articleTagServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleTagServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.articleTag = entity;
        articleTagServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleTagServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
