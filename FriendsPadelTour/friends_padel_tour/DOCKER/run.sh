

echo "To run this scrip it is necessary to be on the directory webapp1, make sure that you have the project on your machine and the directory is correct. If you need to clone the project press Y"
read VAR
if [[ $VAR = Y && $VAR = y ]]
then
    mkdir FriendsPadelTourApp
    cd FriendsPadelTourApp
    git clone https://github.com/CodeURJC-DAW-2020-21/webapp1
fi


# CREATE THE .JAR WITH THE PROJECT
cd "FriendsPadelTour/friends_padel_tour/DOCKER"

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




# CREATE THE DOCKER IMAGE WITH THE DOCKERFILE

cd "DOCKER"

# Build the docker image

docker build -t friendspadeltour .

# START THE APP WITH THE DB USING DOCKER-COMPOSE

docker-compose up -d