export interface IArticleLanguage {
  id?: number;
  articleId?: string;
  languageId?: number;
}

export class ArticleLanguage implements IArticleLanguage {
  constructor(public id?: number, public articleId?: string, public languageId?: number) {}
}
