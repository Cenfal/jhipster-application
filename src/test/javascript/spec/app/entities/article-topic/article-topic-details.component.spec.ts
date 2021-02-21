/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ArticleTopicDetailComponent from '@/entities/article-topic/article-topic-details.vue';
import ArticleTopicClass from '@/entities/article-topic/article-topic-details.component';
import ArticleTopicService from '@/entities/article-topic/article-topic.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ArticleTopic Management Detail Component', () => {
    let wrapper: Wrapper<ArticleTopicClass>;
    let comp: ArticleTopicClass;
    let articleTopicServiceStub: SinonStubbedInstance<ArticleTopicService>;

    beforeEach(() => {
      articleTopicServiceStub = sinon.createStubInstance<ArticleTopicService>(ArticleTopicService);

      wrapper = shallowMount<ArticleTopicClass>(ArticleTopicDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { articleTopicService: () => articleTopicServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundArticleTopic = { id: 123 };
        articleTopicServiceStub.find.resolves(foundArticleTopic);

        // WHEN
        comp.retrieveArticleTopic(123);
        await comp.$nextTick();

        // THEN
        expect(comp.articleTopic).toBe(foundArticleTopic);
      });
    });
  });
});
