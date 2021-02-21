/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleLanguageComponent from '@/entities/article-language/article-language.vue';
import ArticleLanguageClass from '@/entities/article-language/article-language.component';
import ArticleLanguageService from '@/entities/article-language/article-language.service';

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
  describe('ArticleLanguage Management Component', () => {
    let wrapper: Wrapper<ArticleLanguageClass>;
    let comp: ArticleLanguageClass;
    let articleLanguageServiceStub: SinonStubbedInstance<ArticleLanguageService>;

    beforeEach(() => {
      articleLanguageServiceStub = sinon.createStubInstance<ArticleLanguageService>(ArticleLanguageService);
      articleLanguageServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ArticleLanguageClass>(ArticleLanguageComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          articleLanguageService: () => articleLanguageServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      articleLanguageServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllArticleLanguages();
      await comp.$nextTick();

      // THEN
      expect(articleLanguageServiceStub.retrieve.called).toBeTruthy();
      expect(comp.articleLanguages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      articleLanguageServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeArticleLanguage();
      await comp.$nextTick();

      // THEN
      expect(articleLanguageServiceStub.delete.called).toBeTruthy();
      expect(articleLanguageServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
