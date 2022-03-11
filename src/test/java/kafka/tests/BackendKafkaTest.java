package kafka.tests;

import java.util.Collection;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.testng.annotations.Test;
import com.oleh.database.DbService;
import com.oleh.database.model.Deal;
import com.oleh.kafka.KafkaRecord;
import com.oleh.kafka.KafkaService;
import com.oleh.kafka.consumer.KafkaMessageConsumer;
import com.oleh.kafka.model.RequestMessage;
import com.oleh.kafka.producer.KafkaMessageProducer;

public class BackendKafkaTest {

    @Test
    public void testCanWriteMessageToQueueingTransaction() {
        KafkaMessageProducer kafkaMessageProducer = new KafkaMessageProducer("localhost:9092");
        kafkaMessageProducer.createProducer();
        KafkaMessageConsumer kafkaMessageConsumer = new KafkaMessageConsumer("localhost:9092");
        KafkaService kafkaService = new KafkaService(kafkaMessageProducer,kafkaMessageConsumer) ;
        kafkaService.subscribe("test2");
        kafkaService.consume();

//        RecordMetadata metadata = kafkaService.send("test2", "oleh test 7");
        RequestMessage requestMessage = kafkaService.send();
        kafkaService.waitForMessages();

        Collection<KafkaRecord> receivedRecords = kafkaService.getReceivedRecords();
        System.out.println();
    }

    @Test
    public void databaseTest(){
        DbService dbService = new DbService();
        Deal dealById = dbService.getDealById(1);
        System.out.println();
    }
}
