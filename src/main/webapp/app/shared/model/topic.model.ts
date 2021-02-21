export interface ITopic {
  id?: number;
  name?: string;
  statusId?: number;
}

export class Topic implements ITopic {
  constructor(public id?: number, public name?: string, public statusId?: number) {}
}
