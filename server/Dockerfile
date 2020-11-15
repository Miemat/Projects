FROM openjdk:14.0.2
ADD build/libs/projectCalendar-0.0.1-SNAPSHOT.jar .
COPY src/main/resources/application.properties .
EXPOSE 8081
CMD java -jar projectCalendar-0.0.1-SNAPSHOT.jar --envname=prod