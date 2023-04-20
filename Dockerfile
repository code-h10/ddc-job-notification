FROM adoptopenjdk/openjdk11

WORKDIR /root/ddc

COPY . /root/ddc

RUN ./gradlew build

CMD java -jar /root/ddc/build/libs/ddc-notification-0.0.1-SNAPSHOT.jar
