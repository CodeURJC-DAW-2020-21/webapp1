import { PadelMatch } from './padelMatch.model';
import { User } from './user.model';

export interface Player{
  id?: number;
  name: string;
  username: string;
  surname: string;
  location: string;
  email: string;
  division: number;
  hasImage: boolean;
  imagePath: string;
  matchesWon: number;
  matchesLost: number;
  matchesPlayed: number;
  createdMatches: PadelMatch[];
  pendingMatches: PadelMatch[];
  playedMatches: PadelMatch[];
  score: number;
  user: User;
  doubles: Player[];
}
