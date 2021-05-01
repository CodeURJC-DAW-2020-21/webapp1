import { User } from "./user.model";

export interface Bussiness{
  id? : number;
  bussinessName : string;
  ownerName : string;
  ownerSurname : string;
  location : string;
  city : string;
  email : string;
  adress : string;
  bussinessType : string;
  imagePath : string;
  hasImage : boolean;
  user : User;
}
