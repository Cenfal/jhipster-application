/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ArticleDetailComponent from '@/entities/article/article-details.vue';
import ArticleClass from '@/entities/article/article-details.component';
import ArticleService from '@/entities/article/article.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Article Management Detail Component', () => {
    let wrapper: Wrapper<ArticleClass>;
    let comp: ArticleClass;
    let articleServiceStub: SinonStubbedInstance<ArticleService>;

    beforeEach(() => {
      articleServiceStub = sinon.createStubInstance<ArticleService>(ArticleService);

      wrapper = shallowMount<ArticleClass>(ArticleDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { articleService: () => articleServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundArticle = { id: 123 };
        articleServiceStub.find.resolves(foundArticle);

        // WHEN
        comp.retrieveArticle(123);
        await comp.$nextTick();

        // THEN
        expect(comp.article).toBe(foundArticle);
      });
    });
  });
});
