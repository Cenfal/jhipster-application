/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArticleTagComponent from '@/entities/article-tag/article-tag.vue';
import ArticleTagClass from '@/entities/article-tag/article-tag.component';
import ArticleTagService from '@/entities/article-tag/article-tag.service';

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
  describe('ArticleTag Management Component', () => {
    let wrapper: Wrapper<ArticleTagClass>;
    let comp: ArticleTagClass;
    let articleTagServiceStub: SinonStubbedInstance<ArticleTagService>;

    beforeEach(() => {
      articleTagServiceStub = sinon.createStubInstance<ArticleTagService>(ArticleTagService);
      articleTagServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ArticleTagClass>(ArticleTagComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          articleTagService: () => articleTagServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      articleTagServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllArticleTags();
      await comp.$nextTick();

      // THEN
      expect(articleTagServiceStub.retrieve.called).toBeTruthy();
      expect(comp.articleTags[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      articleTagServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeArticleTag();
      await comp.$nextTick();

      // THEN
      expect(articleTagServiceStub.delete.called).toBeTruthy();
      expect(articleTagServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
