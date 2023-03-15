<div align="center">
    <h1>📢 Tech Stack </h1>
</div>
<div align="center">
    <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
    <img src="https://img.shields.io/badge/GitLab-FC6D26?style=flat&logo=GitLab&logoColor=white" /><br>
    <img src="https://img.shields.io/badge/spring-6DB33F?style=flat&logo=spring&logoColor=white" />
    <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=springboot&logoColor=white" />
    <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat&logo=springsecurity&logoColor=white" /><br>
    <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white" />
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white" /><br>
    <img src="https://img.shields.io/badge/AmazonAWS-232F3E?style=flat&logo=AmazonAWS&logoColor=white" />
    <img src="https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=JUnit5&logoColor=white" />
</div>
<br>

# Mutsa Final Project 🌈 멋사스네스(MustsaSNS)

## 📚 프로젝트 개요
1️⃣ 로그인   
2️⃣ 회원가입   
3️⃣ 글 작성/수정/삭제/리스트    
4️⃣ 댓글 작성/수정/삭제/리스트    
5️⃣ 좋아요    
6️⃣ 알람    
7️⃣ 마이피드    

1️⃣ ~ 7️⃣ 기능들을 사용하여 회원들끼리 소통하는 SNS 애플리케이션

## 📃 개발환경
- 에디터 : Intellij Ultimate
- 개발 툴 : SpringBoot 2.7.5
- 자바 : JAVA 11
- 빌드 : Gradle 6.8
- 서버 : AWS EC2
- 배포 : Docker
- 데이터베이스 : MySql 8.0
- 필수 라이브러리 : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security

## 🛠 기능
- Swagger
- AWS EC2에 Docker 배포
- Gitlab CI & Crontab CD

## 📢 Swagger
http://ec2-52-79-78-160.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/

## 📊 ERD
![](final_project_erd.png)

## 📊 아키텍쳐
![](img.png)

## 📃 EndPoint
> 회원가입
`POST /api/v1/users/join`    

> 로그인
`POST /api/v1/users/login`    

> 포스트 전체 조회
`GET /api/v1/posts`    

> 포스트 1개 조회
`GET api/v1/posts/{postId}`    

> 포스트 등록
`POST api/v1/posts`    

> 포스트 수정
`PUT api/v1/posts/{postId}`    

> 포스트 삭제
`DELETE /api/v1/posts/{postId}`    

> 댓글 등록
`POST /api/v1/posts/{postId}/comments`    

> 댓글 수정
`PUT /api/v1/posts/{postId}/comments/{id}`    

> 댓글 삭제
`DELETE /api/v1/posts/{postId}/comments/{id}`    

> 좋아요 누르기
`POST /api/v1/posts/{postId}/likes`    

> 좋아요 개수
`GET /api/v1/posts/{postId}/likes`    

> 받은 알람 조회
`GET /api/v1/alarms`
