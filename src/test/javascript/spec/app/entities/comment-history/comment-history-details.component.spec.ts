/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import CommentHistoryDetailComponent from '@/entities/comment-history/comment-history-details.vue';
import CommentHistoryClass from '@/entities/comment-history/comment-history-details.component';
import CommentHistoryService from '@/entities/comment-history/comment-history.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('CommentHistory Management Detail Component', () => {
    let wrapper: Wrapper<CommentHistoryClass>;
    let comp: CommentHistoryClass;
    let commentHistoryServiceStub: SinonStubbedInstance<CommentHistoryService>;

    beforeEach(() => {
      commentHistoryServiceStub = sinon.createStubInstance<CommentHistoryService>(CommentHistoryService);

      wrapper = shallowMount<CommentHistoryClass>(CommentHistoryDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { commentHistoryService: () => commentHistoryServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCommentHistory = { id: 123 };
        commentHistoryServiceStub.find.resolves(foundCommentHistory);

        // WHEN
        comp.retrieveCommentHistory(123);
        await comp.$nextTick();

        // THEN
        expect(comp.commentHistory).toBe(foundCommentHistory);
      });
    });
  });
});
