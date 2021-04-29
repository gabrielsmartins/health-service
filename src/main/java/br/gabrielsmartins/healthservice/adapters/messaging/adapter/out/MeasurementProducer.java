package br.gabrielsmartins.healthservice.adapters.messaging.adapter.out;

import br.gabrielsmartins.healthservice.adapters.messaging.adapter.out.mapper.MeasurementProducerMapper;
import br.gabrielsmartins.healthservice.adapters.messaging.config.TopicProperties;
import br.gabrielsmartins.healthservice.application.domain.Measurement;
import br.gabrielsmartins.healthservice.application.ports.out.NotifyMeasurementPort;
import br.gabrielsmartins.healthservice.common.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

@MessagingAdapter
@RequiredArgsConstructor
@Slf4j
public class MeasurementProducer implements NotifyMeasurementPort {

    private final KafkaTemplate<String, SpecificRecord> template;
    private final TopicProperties topicProperties;
    private final MeasurementProducerMapper mapper;

    @Override
    public Mono<Measurement> notify(Measurement measurement) {
        var message = this.mapper.mapToMessage(measurement);
        String topic = topicProperties.getOutputTopic(TopicProperties.MEASUREMENT_ANALYZED);
        String key = measurement.getPerson().getId().toString();
        log.info("Sending measurement notification: {}", message);
        this.template.send(topic, key,message)
                     .addCallback(result -> log.info("Measurement notification sent sucessfully: {}", result),
                                  ex -> log.error("Error sending measurement notification:{},{}", message, ex));
        return Mono.just(measurement);
    }
}
