FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/bff-agendador-tarefas-0.0.1-SNAPSHOT.jar /app/bff-agendador-tarefas.jar

EXPOSE 8085

CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]