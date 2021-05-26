import dayjs from 'dayjs';

export interface IOrder {
  id?: number;
  customer?: number;
  port?: number;
  dateOfLoading?: string | null;
  dateOfShipment?: string | null;
  status?: number;
  transport?: number;
}

export const defaultValue: Readonly<IOrder> = {};
