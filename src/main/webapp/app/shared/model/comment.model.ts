export interface IComment {
  id?: number;
  comment?: string;
  userID?: string;
  articleId?: string;
  statusId?: number;
}

export class Comment implements IComment {
  constructor(public id?: number, public comment?: string, public userID?: string, public articleId?: string, public statusId?: number) {}
}
