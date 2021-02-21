export interface ITag {
  id?: number;
  tag?: string;
}

export class Tag implements ITag {
  constructor(public id?: number, public tag?: string) {}
}
