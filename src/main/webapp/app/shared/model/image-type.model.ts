export interface IImageType {
  id?: number;
  typeId?: number;
  type?: string;
  statusId?: number;
}

export class ImageType implements IImageType {
  constructor(public id?: number, public typeId?: number, public type?: string, public statusId?: number) {}
}
