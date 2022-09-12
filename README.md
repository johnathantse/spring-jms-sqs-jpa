# spring-jms-sqs-jpa
JMS notifications with AWS SQS when database is updated

Project example that uses following tools:
    - Spring Boot
    - JMS: Java messaging service
    - AWS SQS: AWS Simple Queue Service
    - JPA: Java PErsistence API
    - H2: In memory database
    - Spring Rest

Provides a H2 database and endpoints to implement CRUD on database.
On successful CRUD operations, a service will send notifications to SQS.
A separate package contains a jms listener which will listen to the SQS queue and log when it recieves a message.