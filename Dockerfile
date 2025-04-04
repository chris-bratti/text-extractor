FROM eclipse-temurin:21-jre-alpine

ENV APP_HOME=/app
WORKDIR $APP_HOME

RUN apk add --no-cache curl

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
