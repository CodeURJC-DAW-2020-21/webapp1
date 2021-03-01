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
#
![alt-text](https://github.com/CodeURJC-DAW-2020-21/webapp1/blob/main/Screenshots/Navigation%20Diagram.png?raw=true "Division 1")




