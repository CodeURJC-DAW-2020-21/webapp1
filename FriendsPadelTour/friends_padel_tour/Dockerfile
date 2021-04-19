FROM maven as builder
COPY . /code/
WORKDIR /code
RUN mvn package
FROM openjdk:11-jre
COPY --from=builder /code/target/*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "java-FriendsPadelTour-1.jar" ] 