export interface IAddress {
  id?: number;
  countryCode?: string;
  country?: string;
  cityCode?: string;
  city?: string;
  districtCode?: string;
  district?: string;
  countyCode?: string;
  county?: string;
  neighborhoodCode?: string;
  neighborhood?: string;
  shipmentCode?: string;
  shipment?: string;
  villageCode?: string;
  village?: string;
  streetCode?: string;
  street?: string;
  buildingCode?: string;
  buildingNumber?: string;
  building?: string;
  buildingFree?: string;
  independentPartsCode?: string;
  independentParts?: string;
  independentPartsFree?: string;
  postalCode?: string;
  statusId?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public countryCode?: string,
    public country?: string,
    public cityCode?: string,
    public city?: string,
    public districtCode?: string,
    public district?: string,
    public countyCode?: string,
    public county?: string,
    public neighborhoodCode?: string,
    public neighborhood?: string,
    public shipmentCode?: string,
    public shipment?: string,
    public villageCode?: string,
    public village?: string,
    public streetCode?: string,
    public street?: string,
    public buildingCode?: string,
    public buildingNumber?: string,
    public building?: string,
    public buildingFree?: string,
    public independentPartsCode?: string,
    public independentParts?: string,
    public independentPartsFree?: string,
    public postalCode?: string,
    public statusId?: number
  ) {}
}
