export interface IContact {
  id?: number;
  userId?: string;
  mail?: string;
  phone?: string;
  preferred?: boolean;
  addressId?: string;
  statusId?: number;
}

export class Contact implements IContact {
  constructor(
    public id?: number,
    public userId?: string,
    public mail?: string,
    public phone?: string,
    public preferred?: boolean,
    public addressId?: string,
    public statusId?: number
  ) {
    this.preferred = this.preferred || false;
  }
}
