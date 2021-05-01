export interface PadelMatch{
  id? : number;
  division : number;
  time : string;
  date : string;
  city : string;
  province : string;
  facility : string;
  nPlayers : number;
  hasWinner : boolean;
}
