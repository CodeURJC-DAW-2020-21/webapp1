import { DoubleOfPlayers } from './doubleOfPlayers.model';
export interface PadelMatch{
  id?: number;
  division: number;
  time: string;
  date: string;
  city: string;
  province: string;
  facility: string;
  nPlayers: number;
  hasWinner: boolean;
  double1: DoubleOfPlayers;
  double2: DoubleOfPlayers;
}
