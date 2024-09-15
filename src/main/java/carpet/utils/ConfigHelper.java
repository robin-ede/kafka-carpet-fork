package carpet.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

public class ConfigHelper {

    private static String kafkaUrl;
    private static String kafkaTopic;
    private static String kafkaKey;  // Add a field for the Kafka key

    // Method to load the Kafka configuration from a JSON file
    public static void loadConfig() {
        Gson gson = new Gson();
        try {
            // Specify the path to the config file (you can customize this path)
            FileReader reader = new FileReader("config/kafka_config.json");
            JsonObject config = gson.fromJson(reader, JsonObject.class);

            // Read Kafka URL, Topic, and Key from the JSON file
            kafkaUrl = config.get("kafka_url").getAsString();
            kafkaTopic = config.get("topic").getAsString();
            kafkaKey = config.get("key").getAsString();  // Read the key from the config

            reader.close();
        } catch (IOException e) {
            System.err.println("Could not load Kafka config file.");
            e.printStackTrace();
        }
    }

    public static String getKafkaUrl() {
        return kafkaUrl;
    }

    public static String getKafkaTopic() {
        return kafkaTopic;
    }

    public static String getKafkaKey() {
        return kafkaKey;  // Getter for the Kafka key
    }
}