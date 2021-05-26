export interface IStatus {
  id?: number;
  statusName?: string;
  description?: string;
}

export const defaultValue: Readonly<IStatus> = {};
