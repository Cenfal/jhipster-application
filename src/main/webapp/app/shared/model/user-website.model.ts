export interface IUserWebsite {
  id?: number;
  code?: number;
  name?: string;
  surname?: string;
  formattedName?: string;
  userName?: string;
  statusId?: number;
}

export class UserWebsite implements IUserWebsite {
  constructor(
    public id?: number,
    public code?: number,
    public name?: string,
    public surname?: string,
    public formattedName?: string,
    public userName?: string,
    public statusId?: number
  ) {}
}
