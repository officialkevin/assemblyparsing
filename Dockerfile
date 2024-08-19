#define base docker image
FROM openjdk:22
LABEL maintainers="kevinroland.net"
ADD target/Assemblyparsing-0.0.1-SNAPSHOT.jar assemblyparsingdocker.jar
ENTRYPOINT ["java", "-jar", "assemblyparsingdocker.jar"]