FROM harisekhon/ubuntu-java

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} MicroService2-1.0.jar

ENV JAVA_OPTS="-Xmx128m -Xms64m"

ENTRYPOINT ["java", "-jar", "MicroService1-2.0.jar"]