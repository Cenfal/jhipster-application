export interface IMain {
  id?: number;
  createDate?: Date;
  updateDate?: Date;
  endDate?: Date;
  createdBy?: string;
  updatedBy?: string;
  contactId?: number;
  userWebsiteId?: number;
  profilePhotoId?: number;
  imageId?: number;
  articleHistoryId?: number;
  commentHistoryId?: number;
  articleId?: number;
  commentId?: number;
}

export class Main implements IMain {
  constructor(
    public id?: number,
    public createDate?: Date,
    public updateDate?: Date,
    public endDate?: Date,
    public createdBy?: string,
    public updatedBy?: string,
    public contactId?: number,
    public userWebsiteId?: number,
    public profilePhotoId?: number,
    public imageId?: number,
    public articleHistoryId?: number,
    public commentHistoryId?: number,
    public articleId?: number,
    public commentId?: number
  ) {}
}
