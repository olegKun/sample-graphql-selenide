package com.oleh.kafka;

import lombok.SneakyThrows;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaRecord {
    private final ConsumerRecord<String,String> record;

    public KafkaRecord(ConsumerRecord<String, String> record) {
        this.record = record;
    }

    @SneakyThrows
    public <T> T valueAs(Class<T> tClass){
        return new ObjectMapper().readValue(record.value(),tClass);
    }
}
