FROM harisekhon/ubuntu-java

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} MicroNamingServer-1.0.jar

ENV JAVA_OPTS="-Xmx128m -Xms64m"

ENTRYPOINT ["java", "-jar", "MicroNamingServer-1.0.jar"]