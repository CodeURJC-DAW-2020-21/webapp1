
echo "Docker and docker-compose are the only pre-requirements"
echo "If you don't have this tools prees Y"
read VAR
if [[ $VAR = Y && $VAR = y ]]
then
    exit
fi

cd "FriendsPadelTour/friends_padel_tour/DOCKER"

docker-compose up -d