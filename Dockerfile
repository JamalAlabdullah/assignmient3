FROM gradle:jdk17 AS gradle
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17 as runtime
WORKDIR /app

ENV PROFILE "movie"
ENV DDL "none"
ENV SHOWSQL "false"
ENV INITMODE "never"
ENV PORT 8080


COPY --from=gradle /app/build/libs/*.jar /app/app.jar
RUN chown -R 1000:1000 /app
USER 1000:1000
ENTRYPOINT ["java","-jar", "app.jar"]