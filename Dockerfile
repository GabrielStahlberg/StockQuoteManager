FROM openjdk

EXPOSE 8081

WORKDIR /app

COPY target/stock-quote-manager-0.0.1-SNAPSHOT.jar /app/stock-quote-manager.jar

ENTRYPOINT ["java", "-jar", "stock-quote-manager.jar"]