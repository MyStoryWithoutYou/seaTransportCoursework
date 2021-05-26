export interface ITransport {
  id?: number;
  transportName?: string;
  maxWeight?: number;
  speed?: number;
  deckSize?: number;
}

export const defaultValue: Readonly<ITransport> = {};
