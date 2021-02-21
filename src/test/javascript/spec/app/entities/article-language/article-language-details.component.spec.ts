/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ArticleLanguageDetailComponent from '@/entities/article-language/article-language-details.vue';
import ArticleLanguageClass from '@/entities/article-language/article-language-details.component';
import ArticleLanguageService from '@/entities/article-language/article-language.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ArticleLanguage Management Detail Component', () => {
    let wrapper: Wrapper<ArticleLanguageClass>;
    let comp: ArticleLanguageClass;
    let articleLanguageServiceStub: SinonStubbedInstance<ArticleLanguageService>;

    beforeEach(() => {
      articleLanguageServiceStub = sinon.createStubInstance<ArticleLanguageService>(ArticleLanguageService);

      wrapper = shallowMount<ArticleLanguageClass>(ArticleLanguageDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { articleLanguageService: () => articleLanguageServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundArticleLanguage = { id: 123 };
        articleLanguageServiceStub.find.resolves(foundArticleLanguage);

        // WHEN
        comp.retrieveArticleLanguage(123);
        await comp.$nextTick();

        // THEN
        expect(comp.articleLanguage).toBe(foundArticleLanguage);
      });
    });
  });
});
