/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleTopicComponent from '@/entities/article-topic/article-topic.vue';
import ArticleTopicClass from '@/entities/article-topic/article-topic.component';
import ArticleTopicService from '@/entities/article-topic/article-topic.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('ArticleTopic Management Component', () => {
    let wrapper: Wrapper<ArticleTopicClass>;
    let comp: ArticleTopicClass;
    let articleTopicServiceStub: SinonStubbedInstance<ArticleTopicService>;

    beforeEach(() => {
      articleTopicServiceStub = sinon.createStubInstance<ArticleTopicService>(ArticleTopicService);
      articleTopicServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ArticleTopicClass>(ArticleTopicComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          articleTopicService: () => articleTopicServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      articleTopicServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllArticleTopics();
      await comp.$nextTick();

      // THEN
      expect(articleTopicServiceStub.retrieve.called).toBeTruthy();
      expect(comp.articleTopics[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      articleTopicServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeArticleTopic();
      await comp.$nextTick();

      // THEN
      expect(articleTopicServiceStub.delete.called).toBeTruthy();
      expect(articleTopicServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
