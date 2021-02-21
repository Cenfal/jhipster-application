/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleHistoryComponent from '@/entities/article-history/article-history.vue';
import ArticleHistoryClass from '@/entities/article-history/article-history.component';
import ArticleHistoryService from '@/entities/article-history/article-history.service';

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
  describe('ArticleHistory Management Component', () => {
    let wrapper: Wrapper<ArticleHistoryClass>;
    let comp: ArticleHistoryClass;
    let articleHistoryServiceStub: SinonStubbedInstance<ArticleHistoryService>;

    beforeEach(() => {
      articleHistoryServiceStub = sinon.createStubInstance<ArticleHistoryService>(ArticleHistoryService);
      articleHistoryServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ArticleHistoryClass>(ArticleHistoryComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          articleHistoryService: () => articleHistoryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      articleHistoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllArticleHistorys();
      await comp.$nextTick();

      // THEN
      expect(articleHistoryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.articleHistories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      articleHistoryServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeArticleHistory();
      await comp.$nextTick();

      // THEN
      expect(articleHistoryServiceStub.delete.called).toBeTruthy();
      expect(articleHistoryServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
