/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CommentHistoryComponent from '@/entities/comment-history/comment-history.vue';
import CommentHistoryClass from '@/entities/comment-history/comment-history.component';
import CommentHistoryService from '@/entities/comment-history/comment-history.service';

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
  describe('CommentHistory Management Component', () => {
    let wrapper: Wrapper<CommentHistoryClass>;
    let comp: CommentHistoryClass;
    let commentHistoryServiceStub: SinonStubbedInstance<CommentHistoryService>;

    beforeEach(() => {
      commentHistoryServiceStub = sinon.createStubInstance<CommentHistoryService>(CommentHistoryService);
      commentHistoryServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CommentHistoryClass>(CommentHistoryComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          commentHistoryService: () => commentHistoryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      commentHistoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCommentHistorys();
      await comp.$nextTick();

      // THEN
      expect(commentHistoryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.commentHistories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      commentHistoryServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeCommentHistory();
      await comp.$nextTick();

      // THEN
      expect(commentHistoryServiceStub.delete.called).toBeTruthy();
      expect(commentHistoryServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
