# Spring Boot and Apache Kafka Application 
  This application was written using Apache Kafka and Spring Boot. The main purpose is to learn and apply Apache Kafka structures.
- To run the application, run the 'docker-compose up' command in the terminal !

# Arch of App
![app-arch drawio](https://github.com/user-attachments/assets/67b4d294-01ad-4cfe-820f-209ba592c2e1)

- While the producer sends the message to the topic for data durability, it waits until the message is written to the leader partition. (producer ack config "all")
- Replication factor is set to 3 for data durability. This way, if one of the brokers crashes (internet connection problems, etc.), data will be available on the follower brokers.
- Since there is not much data flow, a single consumer was deemed sufficient. However, a consumer-group structure has been created in case the data flow increases. Thus, data can be consumed faster asynchronously by writing one more consumer for each partition.
- If the consumer cannot consume the message in the topic, it tries to consume the message again 5 times (. If it cannot consume the message again, it saves the data it could not consume in the database and DLT).

# Capturing errors that occur while consuming messages

## DLT (DeadLetter) and Retry topics 
![DeadLetter and Retry topics](https://github.com/user-attachments/assets/4c6fbe9e-8777-4728-af8c-b5f665e18936)

## DLT messages arch
![DeadLetter Queue messages](https://github.com/user-attachments/assets/d5b1a721-93fa-47fb-ad95-c8c3f034fbf1)

