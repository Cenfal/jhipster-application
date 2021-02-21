export interface IStatus {
  id?: number;
  value?: string;
}

export class Status implements IStatus {
  constructor(public id?: number, public value?: string) {}
}
