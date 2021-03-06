import { PadelMatch } from './padelMatch.model';
import { User } from './user.model';

export interface Player{
  id?: number;
  name: string;
  username: string;
  surname: string;
  location: string;
  email: string;
  division: number | null;
  hasImage: boolean;
  imagePath: string;
  mathcesWon: number;
  matchesLost: number;
  mathesPlayed: number;
  createdMatches: PadelMatch[];
  pendingMatches: PadelMatch[];
  playedMatches: PadelMatch[];
  score: number;
  user: User;
  doubles: Player[];
}
