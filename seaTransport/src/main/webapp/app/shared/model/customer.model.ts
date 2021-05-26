export interface ICustomer {
  id?: number;
  organizationName?: string;
  contactPerson?: string;
  country?: string;
  contactPhone?: string;
  email?: string;
}

export const defaultValue: Readonly<ICustomer> = {};
