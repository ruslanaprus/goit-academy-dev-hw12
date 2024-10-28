create WAR
```shell
./gradlew war
```

build docker
```shell
docker build -t travel-servlet:1.0 .
```

run docker
```shell
docker run -d -p 8080:8080 --name travel-servlet --env-file .env travel-servlet:1.0
```

docker PS
```shell
docker ps
```

docker STOP RM
```shell
docker stop travel-servlet && docker rm travel-servlet
```

LOGS
```bash
docker logs travel-servlet
```

# Instructions:
copy `.env.example` -> `.env` and populate with your DB details