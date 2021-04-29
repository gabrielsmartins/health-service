package br.gabrielsmartins.healthservice.adapters.messaging.adapter.out;

import br.gabrielsmartins.healthservice.adapters.messaging.config.TopicProperties;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Map;

import static br.gabrielsmartins.healthservice.application.support.MeasurementSupport.defaultMeasurement;
import static br.gabrielsmartins.healthservice.application.support.PersonSupport.defaultPerson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = true)
@ActiveProfiles("test")
public class MeasurementProducerTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private MeasurementProducer producer;

    @Autowired
    private TopicProperties topicProperties;

    private String topic;

    @BeforeEach
    public void setup() {
        this.topic = this.topicProperties.getOutputTopic(TopicProperties.MEASUREMENT_ANALYZED);
    }

    @Test
    @DisplayName("Given Measurement Message When Send Call Template")
    public void givenMeasurementMessageWhenSendCallTemplate() {

        var person = defaultPerson().build();

        var measurement = defaultMeasurement().build();
        measurement.setPerson(person);

        this.producer.notify(measurement);

        producer.notify(measurement);

        Consumer<String, SpecificRecord> consumer = createConsumer();

        ConsumerRecord<String, SpecificRecord> singleRecord = KafkaTestUtils.getSingleRecord(consumer, topic);

        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.key()).isEqualTo(measurement.getPerson().getId());
        assertThat(singleRecord.value()).isNotNull();
    }


    private Consumer<String, SpecificRecord> createConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(MeasurementProducerTest.class.getSimpleName(), "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        consumerProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        consumerProps.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "mock://http://localhost:8081");
        Consumer<String, SpecificRecord> consumer = new DefaultKafkaConsumerFactory<String, SpecificRecord>(consumerProps).createConsumer();
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }
}
