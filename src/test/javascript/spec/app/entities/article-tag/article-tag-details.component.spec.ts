/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ArticleTagDetailComponent from '@/entities/article-tag/article-tag-details.vue';
import ArticleTagClass from '@/entities/article-tag/article-tag-details.component';
import ArticleTagService from '@/entities/article-tag/article-tag.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ArticleTag Management Detail Component', () => {
    let wrapper: Wrapper<ArticleTagClass>;
    let comp: ArticleTagClass;
    let articleTagServiceStub: SinonStubbedInstance<ArticleTagService>;

    beforeEach(() => {
      articleTagServiceStub = sinon.createStubInstance<ArticleTagService>(ArticleTagService);

      wrapper = shallowMount<ArticleTagClass>(ArticleTagDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { articleTagService: () => articleTagServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundArticleTag = { id: 123 };
        articleTagServiceStub.find.resolves(foundArticleTag);

        // WHEN
        comp.retrieveArticleTag(123);
        await comp.$nextTick();

        // THEN
        expect(comp.articleTag).toBe(foundArticleTag);
      });
    });
  });
});
