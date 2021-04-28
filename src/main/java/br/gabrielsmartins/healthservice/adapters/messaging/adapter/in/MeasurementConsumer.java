package br.gabrielsmartins.healthservice.adapters.messaging.adapter.in;

import br.gabrielsmartins.healthservice.adapters.messaging.adapter.in.mapper.MeasurementConsumerMapper;
import br.gabrielsmartins.healthservice.application.ports.in.SaveMeasurementUseCase;
import br.gabrielsmartins.healthservice.common.MessagingAdapter;
import br.gabrielsmartins.schemas.measurement_received.MeasurementReceived;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingAdapter
@RequiredArgsConstructor
@Slf4j
@KafkaListener(topics = {"${topic.input.measurement-collected}"})
public class MeasurementConsumer {

    private final SaveMeasurementUseCase useCase;
    private final MeasurementConsumerMapper mapper;

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers, @Payload MeasurementReceived message,  Acknowledgment acknowledgment){
        try{
            log.info("Receiving measurement: {},{}", headers, message);
            var measurement = this.mapper.mapToDomain(message);
            this.useCase.save(measurement);
            log.info("Measurement stored successfully");
        }catch (Exception e){
            log.error("Error consuming message", e);
        }finally {
            acknowledgment.acknowledge();
        }
    }

    @KafkaHandler(isDefault = true)
    public void consume(Object object){
        log.warn("Unrecognized message: {}", object);
    }


}
