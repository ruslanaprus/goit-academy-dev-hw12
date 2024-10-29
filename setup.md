# Setup Instructions (Docker)

Prerequisites:
- Docker
- PostgresSQL database

1. Clone/download this repository https://github.com/ruslanaprus/goit-academy-dev-hw12
2. Copy the `.env.example` file into `.env`, and populate it with your DB details (you might need to change the GOIT_DB_URL if yours is different)
3. You will also need to copy your database credentials into either:
   1. Your environment variables on your computer (keys: [GOIT_DB_URL, GOIT_DB_USER, GOIT_DB_PASS])
   2. The `build.gradle` file in the flyway section
4. Perform a flyway migration to initialise your database with the required data `./gradlew flywayMigrate`
5. Build the docker image `docker build -t travel-servlet:1.0 .`
6. Once the image is built, run the image `docker run -d -p 8080:8080 --name travel-servlet --env-file .env travel-servlet:1.0` (Note this will use the .env file we setup earlier)
7. You can then visit the website at http://localhost:8080/

