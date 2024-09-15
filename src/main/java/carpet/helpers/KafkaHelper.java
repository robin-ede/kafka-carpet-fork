package carpet.helpers;

import carpet.utils.ConfigHelper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;


public class KafkaHelper {

    private static Producer<String, String> producer;

    public static void initializeKafkaProducer() {
        // Load Kafka config before initializing the producer
        ConfigHelper.loadConfig();

        Properties props = new Properties();
        props.put("bootstrap.servers", ConfigHelper.getKafkaUrl());  // Use the Kafka URL from the config file
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);

        try {
            producer.send(new ProducerRecord<>("topic-name", "key", "value")).get();  // Ensure .get() waits for completion
            System.out.println("Message sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String key, String value) {
        // Send the message to the topic specified in the config file
        producer.send(new ProducerRecord<>(ConfigHelper.getKafkaTopic(), key, value));
    }

    public static void closeProducer() {
        producer.close();
    }
}
