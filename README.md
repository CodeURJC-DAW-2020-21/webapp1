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


# PHASE 0


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
This entity will be very easy to use. In case some of the players want a friendly match only need to request a "Friendly match" until other players want to play versus him. ALso include the tournaments' matches.
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

### ALGORITHM USED
The developing team is going to create an algorithm which automatically generates a ranking taking into account the player points and victories, even the category's player that is taking part for. The ranking is going to display in one of the screens we have created and the rest of users can see all the results and stadistics the top players have. The ranking is going to be updated everyday and will show all the changes which have been produced.
