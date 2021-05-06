import { Tournament } from './tournament.model';
import { User } from './user.model';

export interface Bussiness{
  id?: number;
  username: string;
  bussinessName: string;
  ownerName: string;
  ownerSurname: string;
  location: string;
  city: string;
  email: string;
  adress: string;
  bussinessType: string;
  imagePath: string;
  hasImage: boolean;
  user: User;
  tournaments: Tournament[];
  createdTournaments: number;
}
