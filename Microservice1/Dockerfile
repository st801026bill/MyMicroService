FROM harisekhon/ubuntu-java

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} MicroService1-1.0.jar

ENV JAVA_OPTS="-Xmx128m -Xms64m"

ENTRYPOINT ["java", "-jar", "MicroService1-1.0.jar"]