/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleTopicUpdateComponent from '@/entities/article-topic/article-topic-update.vue';
import ArticleTopicClass from '@/entities/article-topic/article-topic-update.component';
import ArticleTopicService from '@/entities/article-topic/article-topic.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('ArticleTopic Management Update Component', () => {
    let wrapper: Wrapper<ArticleTopicClass>;
    let comp: ArticleTopicClass;
    let articleTopicServiceStub: SinonStubbedInstance<ArticleTopicService>;

    beforeEach(() => {
      articleTopicServiceStub = sinon.createStubInstance<ArticleTopicService>(ArticleTopicService);

      wrapper = shallowMount<ArticleTopicClass>(ArticleTopicUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          articleTopicService: () => articleTopicServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.articleTopic = entity;
        articleTopicServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleTopicServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.articleTopic = entity;
        articleTopicServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(articleTopicServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
