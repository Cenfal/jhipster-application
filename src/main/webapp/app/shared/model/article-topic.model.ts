export interface IArticleTopic {
  id?: number;
  articleId?: string;
  topicId?: number;
}

export class ArticleTopic implements IArticleTopic {
  constructor(public id?: number, public articleId?: string, public topicId?: number) {}
}
