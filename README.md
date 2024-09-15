# Carpet Mod with Kafka Integration

This is a fork of the [**Carpet Mod**](https://github.com/gnembon/fabric-carpet/) for Minecraft 1.21.1 that includes Kafka integration to stream item data from Minecraft to a Kafka topic. The mod sends item data whenever a hopper counter is active.

## Installation

To use this mod, you'll need to build it from source.

### Building the Mod

1. Clone the repository:
   ```bash
   git clone https://github.com/robin-ede/kafka-carpet-fork.git
   ```
   
2. Navigate to the project directory:
   ```bash
   cd forked-carpet-mod
   ```

3. Build the mod using **Gradle**. Make sure to **shade** the dependencies during the build process to include Kafka-related libraries in the final `.jar` file:
   ```bash
   ./gradlew shadowJar
   ```

   The built `.jar` file will be available in the `build/libs` directory.

4. Place the built `.jar` file in the `mods` folder of your Minecraft installation.

## Configuration

After installing the mod, you need to configure Kafka by creating or modifying the `kafka_config.json` file in your Minecraft `config` directory (`config/kafka_config.json`).

Example configuration:

```json
{
    "kafka_url": "YOUR_KAFKA_BROKER_URL:PORT",
    "topic": "YOUR_KAFKA_TOPIC",
    "key": "YOUR_EVENT_KEY"
}
```

### Configuration Fields:
- **kafka_url**: The Kafka broker URL and port (e.g., `localhost:9092`).
- **topic**: The Kafka topic where item data will be sent (e.g., `minecraft-items`).
- **key**: The event key used for Kafka messages (e.g., `item_event`).

## Functionality

- When a carpet mod **hopper counter** is active, the mod sends item data (wool type, item type, quantity, etc.) to the specified Kafka topic.
- The mod works seamlessly with any standard Minecraft hopper setup and provides real-time item event streaming to Kafka.

---

### Important Notes:

- Make sure to **shade Kafka dependencies** into the final `.jar` file by using `shadowJar` in Gradle, so they are bundled and available during runtime.
- Ensure that the Kafka broker is running and reachable by the Minecraft server.
