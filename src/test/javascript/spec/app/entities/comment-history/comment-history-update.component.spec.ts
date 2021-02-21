/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import CommentHistoryUpdateComponent from '@/entities/comment-history/comment-history-update.vue';
import CommentHistoryClass from '@/entities/comment-history/comment-history-update.component';
import CommentHistoryService from '@/entities/comment-history/comment-history.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('CommentHistory Management Update Component', () => {
    let wrapper: Wrapper<CommentHistoryClass>;
    let comp: CommentHistoryClass;
    let commentHistoryServiceStub: SinonStubbedInstance<CommentHistoryService>;

    beforeEach(() => {
      commentHistoryServiceStub = sinon.createStubInstance<CommentHistoryService>(CommentHistoryService);

      wrapper = shallowMount<CommentHistoryClass>(CommentHistoryUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          commentHistoryService: () => commentHistoryServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.commentHistory = entity;
        commentHistoryServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(commentHistoryServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.commentHistory = entity;
        commentHistoryServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(commentHistoryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
