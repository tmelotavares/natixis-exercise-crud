export interface Address {
  street: string;
  number: string;
  complement?: string;
  postalCode: string;
  council: string;
  district: string;
}

export interface Client {
  id?: number;
  name: string;
  dateOfBirth: string;
  fiscalId: string;
  addresses: Address[];
}
