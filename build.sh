cd "FriendsPadelTour/friends_padel_tour"

 #mvn clean install
 sudo docker run --rm -v "$PWD":/data -w /data maven mvn package



 # COPY THE .JAR ON THE DOCKER FOLDER
 BUILD_FILENAME="target/*.jar"
 DEST_FILENAME="DOCKER"

 # TO BE SURE ON THE JAR
 cd DOCKER
 sudo rm -rf *.jar

 cd ..
 cp $BUILD_FILENAME $DEST_FILENAME





 # Build the docker image

 docker build -t friendspadeltour ./DOCKER
