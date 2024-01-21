# HEC-BE 🦆
![header](https://capsule-render.vercel.app/api?type=waving&color=7CB332&height=200&section=header&text=한강생태&fontSize=75&fontColor=fff)
## 기술스택
![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white)

![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white)
![jwt](https://img.shields.io/badge/jwt-000000?style=flat-square&logo=JSON%20web%20tokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=Swagger&logoColor=black)
![JPA](https://img.shields.io/badge/JPA-59666C?style=flat-square&logo=Hibernate&logoColor=white)

![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white)
![GCP](https://img.shields.io/badge/GCP-4285F4?style=flat-square&logo=Google%20Cloud&logoColor=white)

## API 명세서

![image](https://github.com/farm-6/HEC-AI/assets/67987132/28a53b53-15da-4a32-8311-545094f8c526)


## 실행
```bash
git clone https://github.com/farm-6/HEC-AI.git
```
### src/main/resources/application.yml
```yml
spring:
  config:
    import:
      - app.yml
  datasource:
    url: jdbc:mysql://database:3306/{your database name}?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: {your password}

  jpa:
    hibernate:
      dialect: org.hibernate.dialect.MySQL5InnoDBDialect
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
logging:
  level:
    farm: info
```

### src/main/resources/app.yml
```yml
app:
  jwt:
    password: { social login password }
    secret: { jwt secret key }
    accessToken:
      expiration: 3600
    refreshToken:
      expiration: 86400
```

### .env.db
```bash
sudo vim .env.db
```
```text
MYSQL_HOST=localhost {your host}
MYSQL_PORT=3306
MYSQL_ROOT_PASSWORD={your password}
MYSQL_USER={your user name}
MYSQL_PASSWORD={your password}
MYSQL_DATABASE={your database name}
```

### docker-compose
```bash
sudo docker-compose up --build -d
```
