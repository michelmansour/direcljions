
FROM java:8-alpine
MAINTAINER Michel Mansour

ADD target/uberjar/direcljions.jar /direcljions/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/direcljions/app.jar"]
