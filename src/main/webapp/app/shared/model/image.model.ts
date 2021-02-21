export interface IImage {
  id?: number;
  articleId?: string;
  path?: string;
  imageType?: number;
  statusId?: number;
}

export class Image implements IImage {
  constructor(public id?: number, public articleId?: string, public path?: string, public imageType?: number, public statusId?: number) {}
}
