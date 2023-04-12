FROM adoptopenjdk/openjdk11

WORKDIR /root/ddcjob

COPY . /root/ddcjob

RUN ./gradlew build

CMD java -jar /root/ddcjob/build/libs/ddcjob-0.0.1-SNAPSHOT.jar
