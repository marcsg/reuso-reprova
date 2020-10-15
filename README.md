# Reprova 2.0

**Author**: Marcelo Santos Guimaraes  
**Email**: marcsgbh@gmail.com

Application created for the discipline Software Reuse of DCC/UFMG at semester 2020/1. It is a system that provides features to manage questions and exams and uses several topics of the discipline such as AOP, libs, Design Patterns, load-time config, among others.  

## Stack
- Spring
- MongoDB
- AspectJ
- Apache Commons Math
- Lombok

Note: Since this application uses Lombok, you need to activate Lombok in your IDE (and maybe Annotation Processing setting) 

## Running the service
### Requirements
* Maven
* Docker if you want to run with Docker containers
* If running without Docker, it requires MongoDB
### Initial Setup
Run a maven install to generate `.jar` file in `target` directory:
```shell
mvn clean install
```

### Run Application
You can run the service locally by manually setting up a local mongoDB instance or using Docker.

- **Local MongoDB Deamon**
> MongoDB Install with Homebrew
```shell
$ brew install mongodb
$ mkdir -p /data/db
$ sudo chown -R `id -un` /data/db
```
Run Mongo daemon `mongod` in a separate tab to start the database.

> Run Application
```shell
mvn spring-boot:run
```

- **Docker**
```shell
$ docker-compose up --build
```

## APIs

In a standard usage of the APIs, the following sequence would be used:
1. Create a question
1. Create or generate an Exam
1. Add grades to a question (stats are calculated during this operation)
1. Calculate Exam grades

### Questions API
#### Create a question

POST http://localhost:8080/questions/

```json
{
    "themes": ["Reuse"],
    "pvt": true,
    "tags": ["test", "reuso", "question"],
    "description": "Question Number 2.",
    "statement": "Another statement: This one starts with an A",
    "difficulty": "HARD"
}
```

#### Find all questions

GET http://localhost:8080/questions

#### Find question by Id

GET http://localhost:8080/questions/5f7542a9e73b2b08f9555c64

#### Add grades to a question

Note: This operation automatically calculate stats of the grades (min, max, standard deviation, etc.)

PUT http://localhost:8080/questions/5f7542a9e73b2b08f9555c64/grades
```json
{
    "year": 2020,
    "semester": 2,
    "discipline": "Software Reuse",
    "grades": [
        {"student": "1", "grade": 7},
        {"student": "2", "grade": 5.8},
        {"student": "3", "grade": 2},
        {"student": "4", "grade": 7},
        {"student": "5", "grade": 4.5},
        {"student": "6", "grade": 2},
        {"student": "7", "grade": 8},
        {"student": "8", "grade": 9.5}
    ]
}
```

### Exams API
#### Create an Exam with a list of questions

POST http://localhost:8080/exams

```json
{
    "title": "Second exam of 2020/2",
    "year": 2020,
    "semester": 2,
    "questions": [
        {"id": "5f7542a9e73b2b08f9555c64"},
        {"id": "5f73d7cd335b8b7be6c29e0e"}
    ]
}
```

#### Generate an Exam with Random Questions
POST http://localhost:8080/exams/generator
```json
{
    "strategyType": "RANDOM",
    "totalQuestions": 2,
    "title": "Sample exam created with Random strategy",
    "year": 2021,
    "semester": 2,
    "saveExam": true
}
```

#### Delete An Exam by Id
DELETE http://localhost:8080/exams/5f73e545d941f95aee79be3c

#### Find Exam By Id
GET http://localhost:8080/exams/5f8129a78e15f67aea9e30c0

#### Find All Exams
GET http://localhost:8080/exams

#### Calculate Exam Grades
PUT http://localhost:8080/exams/5f754437320586688f55930f/grades
