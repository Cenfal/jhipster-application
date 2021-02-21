export interface IProfilePhoto {
  id?: number;
  userId?: string;
  path?: string;
  statusId?: number;
}

export class ProfilePhoto implements IProfilePhoto {
  constructor(public id?: number, public userId?: string, public path?: string, public statusId?: number) {}
}
