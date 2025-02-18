FROM maven:3.9.9 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM  openjdk:21-oracle

COPY --from=build /app/target/mq-to-kafka-0.0.1-SNAPSHOT.jar /app/app.jar


FROM icr.io/ibm-messaging/mq
USER 1001
COPY 20-config.mqsc /etc/mqm/


WORKDIR /app

EXPOSE 8080
EXPOSE 9443

CMD ["java", "-jar", "app.jar"]