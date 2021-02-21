import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here
const BlogMainPage = () => import('@/blog/main-page/main-page.vue')
export default [
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  {
    path: '/blog-main-page',
    name: 'BlogMainPage',
    component: BlogMainPage,
    meta: { authorities: [Authority.USER] },
  },
];
