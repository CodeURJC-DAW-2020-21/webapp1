 ![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/LOGO.png?raw=true "APP LOGO")

# Friends-Padel-Tour
It is a Social Network where you can find players of your same level to play with. Create tournaments with your friends and compete with them in a friendly way.

# MEMBERS
| Name | Email | User GitHub |
| ------------- | ------------- | ------------- |
| Juan Guillo Bermejo | j.guillo.2018@alumnos.urjc.es  | JGBURJC  |
| Eduardo Ivorra Salinas | e.ivorra.2018@alumnos.urjc.es | EduardoIvorra |
| Eduardo Villaverde Espeso | e.villaverdee@alumnos.urjc.es | eduvilla97  |
| Alberto Morán Cuenca | a.moranc.2018@alumnos.urjc.es | AlbertoMoranCuenca  |


# STAGE 0


### ENTITIES
#### - Player user: 
These are the main users who can control almost all the app. This users will have the posibility to search a teammate to play with, play and search some tournaments, and other aspects such as looking the rankings, move in different screens, change their profile, contact with the developing group and a lot of more.
#### - Anonymous user: 
These users can visit our aplication, but they have some restrictions because they aren't singing in. They can move over the screens and consult different information, however they won't be able to search tournaments and matches or visit de differents categories and rankings.  
#### - Admin user: 
This is the secondary user whose main function will be create and organize the tournaments,who always keeps in contact with the companies (Company user).
#### - Company user: 
This user is similar to players, but with the diffence that they can organize some tournaments with admin's help. They have some padel courts that can offer to play the tournaments and making available to play. 
#
#### - Tournaments: 
This entity will be used to organize tournaments where the players can participate. These tournaments will be created by the companies with the help of the admin. (The admin will receive a peticion by a company that wants to create it, and the admin develop and planificate everything according with the information the company has made).
#### - Matches: 
This entity will be very easy to use. In case some of the players want a friendly match only need to request a "Friendly match" until other players want to play versus him. Also include the tournaments' matches.
#### - Padel Doubles:
This will be used to create and collaborate with other players with the object of forming a new padel double, in case yuo don't have a teammate.
#
### SCREENS
- Main Screen: This screen will be used to join the rest and to link all the screens. In this screen you can see interesting information about the recent events of padel like tournaments and friendly matches. It's the principal screen of our app.
- Sign in: The team need a meeting to see what we are going to develop.
- About us: The team need a meeting to see what we are going to develop.
- Your profile: The team need a meeting to see what we are going to develop.
- Search for friendly matches: The team need a meeting to see what we are going to develop.
- Search Tournamentent: The team need a meeting to see what we are going to develop.
- Organize a tournament: This screen can be used only by the admin and companies. In the screen you could organize a tournament,
#
### EXTRA FEATURES
Our team has decided to implement one extra funcionality. When somenone win a tournament the app is going to give a prize to the winner user in the form of diploma. This diploma is going to generate a PDF that permits winners to download, keep and print it. 
#
### ALGORITHM USED
The developing team is going to create an algorithm which automatically generates a ranking taking into account the player points and victories, even the category's player that is taking part for. The ranking is going to display in one of the screens we have created and the rest of users can see all the results and stadistics the top players have. The ranking is going to be updated everyday and will show all the changes which have been produced.
#
### GRAPHICS

# STAGE 1
In this stage we have made all the templates and design of the web. Then, we are going to include the screenshots of every screen and the navigation that every user can do in the web.
#
### SCREENSHOTS

#### - Previous signUp
This screen will be used for all the users that want to sign up in our App. There are 2 differents users, bussiness user and normal user.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/PreviousSingUp.png?raw=true "Previous SignUp Screen")

#### - User signUp
Screen where the user can create his profile through a form and create a new account.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/UserSignUp.png?raw=true "User SignUp Screen")

#### - Bussiness signUp
Screen where the bussiness users can create his profile through a form and create a new account.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/BussinessSignUp.png?raw=true "Bussiness SignUp Screen")

#### - Login
This screen will be used when the users have an account and need to login and enter in our application
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/Login.png?raw=true "Login")

#### - Main page (Index)
This is the principal page where all the users can access, even the anonymous users. In this page you can navigate to all the screens.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/Index.png?raw=true "Index")

#### - User profile
All the users have a profile where they can see all their information and also edit their profiles.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/UserProfile.png?raw=true "User Profile")

#### - Bussiness profile
This is the bussiness profile, where this user can edit his profile and see all his information.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/BussinessProfile.png?raw=true "Bussiness Profile")

#### - Friendly matches
In this screen the user can choose a Division according to his level, and the user will navigate to other screen (1st-6th Division, depending on the division he choose)
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/FriendlyMatch.png?raw=true "Friendly Match")

#### - 1st-6th Division (team - team6)
The user can join to a friendly a match in this screen and also create a new one through a form. In this case we are going to add only one screen because the others are the same the only difference are the names and the divisions.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/Team%20Division%201.png?raw=true "Division 1")


#### - Tournaments
In this screen the user can join and see tournaments. Previously, the admin user need to accept the tournament which has been created by the bussiness user in Tournament Request screen.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/Tournaments.png?raw=true "Tournaments")


#### - Tournaments Request
In this screen the bussiness user can create a tournament. Only they need to fill a form and send it. The tournament will be created when the admin accept that tournament.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/TournamentRequest.png?raw=true "Tournament Request")

#### - About Us
Very simple screen where the team explain the main functions of the app and also different information about us.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/AboutUs.png?raw=true "About Us")

#### - Tournamentent management
In this screen the admin user can see all the tournament requests and accept all the tournaments the admin consider appropiated.
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/TournamentManagement.png?raw=true "Division 1")

## Navigation Diagram
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/Navigation%20Diagram.png?raw=true "Division 1")

# STAGE 2

# Development direction:

- Repository: https://github.com/CodeURJC-DAW-2020-21/webapp1
- Development tools: Visual Studio Code, Spring Tool Suite4
- Dependencies: PDFGenerator, MySQL connector
- Follow the next steps to execute the files:
- Open the project with your development tool
- Start a MySQL service in the local host in the port 3306 with a schema called "fptdb"
- Start running the application
- Enter from a browser to: https://localhost:8443

## DEMO  

1) Anonymous user browsing the screens, we see that he cannot access certain functionalities
2) Register with business (Explain about the tournaments)
3) Login with player username2 (password2)
4) View profile
5) Modify image, password and division
6) See graph (related to the games played, etc)
7) Go to the ranking and show that you can access another user's profile but you cannot edit it (if you access yours, yes)
8) Create party
9) Join party, join with the couple
10) Log out and see that you cannot access certain functionalities
11) Register a new player and join the previous match individually
12) Download PDF of friendly matches
13) Log out and join as admin
14) Manage tournaments, see tournaments managed, pending, accept and reject.

## Entity–relationship DATABASE model
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/DiagramaBD.jpeg?raw=true "DataBase Diagram")
## Class and template diagram
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/DiagramaClases.png?raw=true "Class and Template Diagram")

## 5 COMMITS

| Name | Github user | 1 | 2 | 3 | 4 | 5 |
|--------|--------|--------|--------|--------|--------|--------|
|Juan Guillo Bermejo | JGBURJC |[Security Configuration](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/f17d2ad90e8818957c1f9757344f3e697c9fe68c)|[HTTP conexion and control users](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/f17d2ad90e8818957c1f9757344f3e697c9fe68c)|[CSRFHandlerConfiguration](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/f17d2ad90e8818957c1f9757344f3e697c9fe68c)|[DataBaseUsersLoader](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/f17d2ad90e8818957c1f9757344f3e697c9fe68c)|[Errors and fix comments (TEMPLATES)](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/31bf60e0870490f23a28e836412d26776888770e)|
|Eduardo Ivorra Salinas| EduardoIvorra |[Graphics](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/4a3c5d516d66e2f85f93778ddfc22543b29341ee)|[Controllers and changes](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/4cf5729f8413e9f9cec789fead2dbf322d9f36d7)|[PDFgenerator](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/4a3c5d516d66e2f85f93778ddfc22543b29341ee)|[Alghorithm ranking](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/4a3c5d516d66e2f85f93778ddfc22543b29341ee)|[Profile](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/842acc5d918aab62c1727e1a18f0c4108ad80752)|
|Eduardo Villaverde Espeso | eduvilla97 |[MySQL](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/c0edf83b6bfe5c0633afe902b8adb96fe81fc360)|[Modelation](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/a4dae9073bd428a865b5bead4227e27229995257)|[Join match, join tournament and player profile](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/7ab24d56aaffc9d93563eeddb79f3a91c2ba493a)|[Delete match, choose winner..,](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/12b9d7d14d1287d7d846276a3e6ac418fe5e43f4)|[Sign Up and Use of users](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/7ab24d56aaffc9d93563eeddb79f3a91c2ba493a)|
|Alberto Morán Cuenca | AlbertoMoranCuenca |[Ajax](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/f42f7727c09db2441d24f42418559a875078ce4c)|[Tournaments Creation and elimination](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/46d0fd4f14451f505c15651040e5886dae3b4723)|[Bussiness user](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/2f17979839a2eb78c78e40aa2054eff2983472b9)|[Tournaments sevice and repository](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/f89f8dd9b4ecb275b15d332bb466072133ad6e74)|[Bussiness profile](https://github.com/CodeURJC-DAW-2020-21/webapp1/commits/main/2f17979839a2eb78c78e40aa2054eff2983472b9)|

# STAGE 3

# API documentation:

### Java code with SpringDoc
We are going to have a link that use rawgit that pemit to see all the information related with the API:
https://rawcdn.githack.com/CodeURJC-DAW-2020-21/webapp1/f133949a8e1baa305bbe714e712552940d34ee48/FriendsPadelTour/friends_padel_tour/api-docs/api-docs.html

### api-docs.yaml:
https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/FriendsPadelTour/friends_padel_tour/api-docs/api-docs.yaml
 
# Class diagram (Updated):


### Members Participation <a name="members-part-3"></a>

#### Eduardo Villaverde Espeso

| Number | Description | Commit | 
| ------------- | ------------- | ------------- | 
| #1 | ApiDocs | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/dde697eeeefeb9d3e4be6b1916ba14d5b0a61fb6) | 
| #2 | Api Match Controller | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/1befb61d0d4dc511e4be56a25fe9b4e23d6f6667) |
| #3 | Fixing bugs | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/aa31fc3c60f5e417aca7604addb508a68ee57527) | 
| #4 | Integration with the ApiRest | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/0da660e1656d74e767453ccbcbe9aa87c839c6d5) | 
| #5 | Performed general improvements  | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/665e40d79716bd0b19dd69a98c1c195ba3d3548e) 



#### Juan Guillo Bermejo

| Number | Description | Commit | 
| ------------- | ------------- | ------------- |
| #1 | ReadME | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp11/commit/a201da1e84302def70275a4a027771d76f9921b4) | 
| #2 | ApiUsersController | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/874318de753ef1b4ef63b9ed171690695ec0cb49) | 
| #3 | Fixing bugs, general improvements  | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/1467dce6a6fd18f4e4290d07fba7345d911bb0ca) | 
| #4 | Downloading and uploading images with ApiRest | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/eb19998ac73672dcc731bd43ed19f0f55f73e1b0) | 
| #5 | Security for ApiRest | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/a8b03d10dc5f09cf7103b3328474a723d4df8af2) |



#### Eduardo Ivorra Salinas

| Number | Description | Commit |
| ------------- | ------------- | ------------- |
| #1 | ApiTournaments | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/d5f9664477a7c225da27de6248b5985eae8a1450) | 
| #2 | Docker | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp1/commit/db1c38cded9b7969f7740de724b87ab92b5aa724) | 
| #3 | ReadME  | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp11/commit/5664b6d08a0ed86e3c089fb60ee4f6dfc1cc6744) | 
| #4 | General Improvements | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp11/commit/1264119b553ec0bbeeaffb72abeb21f4f50c7c90) |
| #5 | Working docker-compose | [Commit](https://github.com/CodeURJC-DAW-2020-21/webapp11/commit/a1f2861b77de8085e95e1e47de7af0e3160dfe2a) | 


