export interface IArticleTag {
  id?: number;
  articleId?: string;
  tagId?: number;
}

export class ArticleTag implements IArticleTag {
  constructor(public id?: number, public articleId?: string, public tagId?: number) {}
}
