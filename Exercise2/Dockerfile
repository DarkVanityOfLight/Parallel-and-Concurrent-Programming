FROM alpine:latest


RUN apk update &&\
    apk add openjdk

WORKDIR /app

COPY src/ .
COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle.kts .
COPY gradle .
COPY build/libs/Exercise2-1.0-SNAPSHOT.jar .

CMD ["java", "-jar", "Exercise2-1.0-SNAPSHOT.jar"]

# If you want to build it yourself, to confirm
# CMD ["gradlew", "build"]
# WORKDIR /app/build/classes/java/main
# CMD ["java", "Main"]