package com.oleh.kafka;

import lombok.SneakyThrows;

import java.util.Collection;
import org.apache.kafka.clients.producer.RecordMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oleh.kafka.consumer.KafkaMessageConsumer;
import com.oleh.kafka.faker.TestDataGenerator;
import com.oleh.kafka.model.RequestMessage;
import com.oleh.kafka.model.ResponseMessage;
import com.oleh.kafka.producer.KafkaMessageProducer;

public class KafkaService {
    private KafkaMessageProducer kafkaMessageProducer;
    private KafkaMessageConsumer kafkaMessageConsumer;
    private TestDataGenerator testDataGenerator = new TestDataGenerator();

    public KafkaService(KafkaMessageProducer kafkaMessageProducer, KafkaMessageConsumer kafkaMessageConsumer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.kafkaMessageConsumer = kafkaMessageConsumer;
    }

    public KafkaMessageConsumer consumer() {
        return this.kafkaMessageConsumer;
    }

    public RecordMetadata send(String message) {
        return kafkaMessageProducer.send("test2", message);
    }

    @SneakyThrows
    public RequestMessage send() {
        String message = testDataGenerator.generate("data/message.twig");
        kafkaMessageProducer.send("test2", message);
        return new ObjectMapper().readValue(message,RequestMessage.class);
    }

    public RecordMetadata send(String topic, String message) {
        return kafkaMessageProducer.send(topic, message);
    }

    public Collection<KafkaRecord> waitForMessages() {
        return kafkaMessageConsumer.waitForMessages();
    }

    public void subscribe(String topic) {
        kafkaMessageConsumer.subscribe(topic);
    }

    public Collection<KafkaRecord> consume() {
        return kafkaMessageConsumer.consume();
    }


    public Collection<KafkaRecord> getReceivedRecords() {
        return kafkaMessageConsumer.getReceivedRecords();
    }
}
