# Event Streams on Cloud Essentials coding challenge

This coding challenge is part of the [Event Streams on Cloud Essentials course](https://developer.ibm.com/components/event-streams/series/badge-ibm-event-streams-developer-essentials/).

In this challenge, we will first run a small binary application. This application will produce some records to a topic. Then, the task is to:

- write a consumer application that will consume records from the topic 
- recover the secret message put inside one of the records

## Prerequisite

- [Gradle](https://gradle.org/)
- [JDK](https://adoptopenjdk.net/)
- [Eclipse](https://www.eclipse.org/downloads/packages/) or [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

## The Challenge

The challenge is to produce messages by running the producer binary application provided and modify the consumer application to find the secret message.

The producer application will create a topic called `event-streams-coding-challenge` and send records, one of which is the secret message.

The next step will be to edit the example `App.java` application and add a poll loop in order to keep consuming records until it finds the secret message. The secret message is the value of the record that has the following key: `coding-challenge`.

Follow these steps to complete the challenge:
1. Get the challenge code. Click the clone or download button. You can choose to Clone with SSH, Use HTTPS, or Download ZIP with the code. Clone or unzip the repository.


2. Run the producer application by providing the broker address and API key found from your Event Streams Service credentials.
```shell
java -jar coding-challenge-setup.jar <kafka_broker_sasl> <api_key>
```
Ensure you see "`Congratulations! You have successfully setup the topic for the coding challenge.`" in the output to confirm the secret message was correctly written to the topic.

Now it's time to try to recover the secret message!

3. Setup a project for the sample consumer application

First, navigate to the `coding-challenge-consumer` directory:
```shell
cd coding-challenge-consumer
```

Then create a project for your preferred IDE:

```shell
gradle eclipse
```
Or
```shell
gradle idea
```

Finally import the consumer project into your IDE.

4. In the IDE, open `App.java` and modify the code where you see `TODO` comments.

There are 4 `TODO` comments that highlight Consumer logic that is required to complete the challenge.
```java
// TODO Move position to beginning of partition
// TODO: Add consumer poll loop
// TODO: Find the record whose key is equal to KEY ("coding-challenge")
// TODO: Print the record value to discover the secret message
```

5. Once you have finished modifying the consumer code, build the application. 
```shell
gradle clean && gradle build
```

6. Run the consumer application.
```shell
java -jar ./build/libs/coding-challenge-consumer.jar <kafka_broker_sasl> <api_key>
```

If your code does not work, don't panic! Just return to step 4 and rerun steps 5 and 6 after you've made changes.

If you get stuck, review the explanations of the Event Streams sample in [Get hands on experience with Event Streams Java sample](https://developer.ibm.com/tutorials/event-streams-hands-on-java-sample/) as it contains all the logic required to complete the challenge.

# Summary and next steps

Congratulations! You’ve successfully written your first Kafka application and completed the challenge. Be sure that you’ve checked out the [Event Streams cheat sheet](https://developer.ibm.com/articles/event-streams-dev-cheat-sheet/), because it’s packed full of ninja moves that every Event Streams user should know.

If you’re all done and ready to take the quiz and earn your IBM Event Streams Developer Essentials badge, go back to the [IBM Event Streams Developer Essentials badge](https://developer.ibm.com/components/event-streams/series/badge-ibm-event-streams-developer-essentials/) page and click through to the quiz!
