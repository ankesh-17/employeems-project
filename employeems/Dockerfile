#FROM openJdk:11
FROM maven:3.8-jdk-11

WORKDIR /tmp/springapp

COPY . /tmp/springapp

RUN mvn package -DskipTests=true

EXPOSE 8585

CMD java -jar target/employeems-*.jar
