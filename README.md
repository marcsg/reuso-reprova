# Reprova 2.0

Application created for the discipline Software Reuse of DCC/UFMG at semester 2020/1. It is a system that provides features to manage questions and exams and uses several topics of the discipline such as AOP, libs, Design Patterns, load-time config, among others.  

## Stack
Spring
MongoDB
AspectJ
Apache Commons Math
Lombok

## Running the service


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
    "theme": "Reuse",
    "pvt": true,
    "tags": ["test", "reuso", "question"],
    "description": "This is a sample question for testing purposes only.",
    "statement": "Check this statement: This one starts with a C"
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
