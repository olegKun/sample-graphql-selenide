package com.oleh.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.awaitility.Awaitility;
import com.oleh.kafka.KafkaRecord;

public class KafkaMessageConsumer {
    private static KafkaConsumer<String, String> kafkaConsumer;
    private final Collection<KafkaRecord> receivedRecords = new ArrayList<>();
    private final String bootStrapServer;

    public KafkaMessageConsumer(String bootStrapServer) {
        this.bootStrapServer = bootStrapServer;
        kafkaConsumer = new KafkaConsumer<>(createConsumerProperties());
    }


    public Collection<KafkaRecord> consume() {
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ZERO);

//        kafkaConsumer.commitSync();
        records.forEach(r -> receivedRecords.add(new KafkaRecord(r)));
        return receivedRecords;
    }

    public Collection<KafkaRecord> waitForMessages() {
        Awaitility.await().atMost(30, TimeUnit.SECONDS)
                .until(() -> consume().size() > 18);
        return receivedRecords;
    }

    public void subscribe(String topic) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(topic);
        kafkaConsumer.subscribe(objects);
    }

    private Properties createConsumerProperties() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootStrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return props;
    }

    public Collection<KafkaRecord> getReceivedRecords() {
        return receivedRecords;
    }
}
