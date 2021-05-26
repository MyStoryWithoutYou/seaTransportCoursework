export interface IPort {
  id?: number;
  loadingPortLatitude?: number;
  loadingPortLongitude?: number;
  shipmentPortLatitude?: number;
  shipmentPortLongitude?: number;
}

export const defaultValue: Readonly<IPort> = {};
