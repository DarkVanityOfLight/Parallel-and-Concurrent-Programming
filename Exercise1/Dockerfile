FROM alpine:latest

RUN apk update && \
    apk add openjdk

WORKDIR /app

COPY . .

CMD ["gradlew", "run"]