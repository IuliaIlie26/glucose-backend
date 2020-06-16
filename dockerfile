FROM maven:3.6.3-jdk-8-slim as build
WORKDIR /home/app
COPY pom.xml ./
COPY glucose-application  ./glucose-application
COPY glucose-exposition  ./glucose-exposition
COPY glucose-infrastructure  ./glucose-infrastructure
COPY glucose-domain  ./glucose-domain
RUN mvn clean install -DskipTests
FROM open-liberty
COPY --from=build /home/app/glucose-exposition/target/glucose-exposition-0.0.1-SNAPSHOT.war  /opt/ol/wlp/usr/servers/defaultServer/dropins
ENTRYPOINT ["/opt/ol/wlp/bin/server", "run"]
CMD ["defaultServer"]
