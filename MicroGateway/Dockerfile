FROM harisekhon/ubuntu-java

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} MicroGateway-1.0.jar

ENV JAVA_OPTS="-Xmx128m -Xms64m"

ENTRYPOINT ["java", "-jar", "MicroGateway-1.0.jar"]