#!/bin/sh

echo "✅ Starte Flyway-Migration..."
mvn -Dflyway.user=$SPRING_DATASOURCE_USERNAME \
    -Dflyway.password=$SPRING_DATASOURCE_PASSWORD \
    -Dflyway.url=$SPRING_DATASOURCE_URL flyway:migrate

echo "🚀 Starte Spring Boot Anwendung..."
exec java -jar app.jar
