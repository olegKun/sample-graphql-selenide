package com.oleh.kafka.producer;

import lombok.SneakyThrows;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaMessageProducer {
    private static KafkaProducer<String, String> kaskaProducer;
    private final String bootStrapServer;

    public KafkaMessageProducer(String bootStrapServer) {
        this.bootStrapServer = bootStrapServer;
    }

    public KafkaMessageProducer createProducer() {
        Properties properties = createProducerProperties();
        if (kaskaProducer == null) {
            kaskaProducer = new KafkaProducer<>(properties);
        }
        return this;
    }

    private Properties createProducerProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootStrapServer);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "testClientId");
        props.put("acks", "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @SneakyThrows
    public RecordMetadata send(String topic, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        //        try {
        return kaskaProducer.send(record).get();
        //        } catch (InterruptedException | ExecutionException e) {
        //           throw new RuntimeException("Error");
        //        }
    }
}
