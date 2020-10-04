# reprova-reuso

## Alguns Tópicos da Disciplina utilizados
* Runtime configuration usando Qualifiers
* Layered architecture
* ControllerAdvices - funcionam como facades (??)

## Some implemntation decisions
* Question grades for a given semester can be updated but only entirely. The existing grade list will be replaced by the new one. The statistics are re-calculated on every grade update.


Create question

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


Create an Exam referencing an exam

POST http://localhost:8080/exams

```json
{
    "title": "First exam of 2020/2",
    "year": 2020,
    "semester": 2,
    "questions": [{"id": "5f73d7cd335b8b7be6c29e0e"}]
}
```

Notes:
1. Import ssh key to ssh-agent:
```bash
ssh-add -K ~/.ssh/id_rsa_ufmg
```
