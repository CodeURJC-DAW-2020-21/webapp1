FROM node as node
COPY ../frontend /app/
WORKDIR /app
RUN npm ci
RUN npm run build --prod --base-href="/new/"


FROM maven as builder
COPY . /code/
WORKDIR /code
RUN mvn package



FROM openjdk:11-jre
COPY --from=builder /code/target/*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "friends_padel_tour-0.0.1-SNAPSHOT.jar" ] 