import { Tournament } from './tournament.model';
import { DoubleOfPlayers } from './doubleOfPlayers.model';
import { Player } from './player.model';
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
  playerCreator: Player;
  doubleWinner: DoubleOfPlayers;
  tournament: Tournament;
}
