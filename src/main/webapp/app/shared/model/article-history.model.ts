export interface IArticleHistory {
  id?: number;
  articleId?: string;
  updateTime?: number;
}

export class ArticleHistory implements IArticleHistory {
  constructor(public id?: number, public articleId?: string, public updateTime?: number) {}
}
