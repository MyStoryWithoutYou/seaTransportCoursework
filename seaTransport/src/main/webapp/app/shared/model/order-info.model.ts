export interface IOrderInfo {
  id?: number;
  order?: number;
  length?: number;
  width?: number;
  height?: number;
  volume?: number;
  weight?: number;
}

export const defaultValue: Readonly<IOrderInfo> = {};
