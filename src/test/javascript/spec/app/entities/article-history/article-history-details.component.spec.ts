/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ArticleHistoryDetailComponent from '@/entities/article-history/article-history-details.vue';
import ArticleHistoryClass from '@/entities/article-history/article-history-details.component';
import ArticleHistoryService from '@/entities/article-history/article-history.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ArticleHistory Management Detail Component', () => {
    let wrapper: Wrapper<ArticleHistoryClass>;
    let comp: ArticleHistoryClass;
    let articleHistoryServiceStub: SinonStubbedInstance<ArticleHistoryService>;

    beforeEach(() => {
      articleHistoryServiceStub = sinon.createStubInstance<ArticleHistoryService>(ArticleHistoryService);

      wrapper = shallowMount<ArticleHistoryClass>(ArticleHistoryDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { articleHistoryService: () => articleHistoryServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundArticleHistory = { id: 123 };
        articleHistoryServiceStub.find.resolves(foundArticleHistory);

        // WHEN
        comp.retrieveArticleHistory(123);
        await comp.$nextTick();

        // THEN
        expect(comp.articleHistory).toBe(foundArticleHistory);
      });
    });
  });
});
