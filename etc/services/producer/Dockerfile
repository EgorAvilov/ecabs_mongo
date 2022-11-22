FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine
EXPOSE ${PRODUCER_SERVER_PORT}
WORKDIR /producer

COPY .mvn/ .mvn
COPY mvnw ./producer/pom.xml ./
COPY ./producer/src ./src

RUN ./mvnw dependency:resolve
CMD ["./mvnw", "spring-boot:run"]

