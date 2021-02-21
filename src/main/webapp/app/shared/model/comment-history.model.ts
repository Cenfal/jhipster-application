export interface ICommentHistory {
  id?: number;
  commentId?: string;
  updateTime?: number;
}

export class CommentHistory implements ICommentHistory {
  constructor(public id?: number, public commentId?: string, public updateTime?: number) {}
}
