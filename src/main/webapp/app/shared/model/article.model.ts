export interface IArticle {
  id?: number;
  code?: number;
  title?: string;
  text?: string;
  userId?: string;
  statusId?: number;
}

export class Article implements IArticle {
  constructor(
    public id?: number,
    public code?: number,
    public title?: string,
    public text?: string,
    public userId?: string,
    public statusId?: number
  ) {}
}
