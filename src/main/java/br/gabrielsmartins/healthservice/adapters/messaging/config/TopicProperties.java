package br.gabrielsmartins.healthservice.adapters.messaging.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "topic")
@Data
@Getter
public class TopicProperties {

    public static final String MEASUREMENT_COLLECTED = "measurement-collected";
    public static final String MEASUREMENT_ANALYZED = "measurement-analyzed";

    private Map<String, String> input;
    private Map<String, String> output;

    public String getInputTopic(String topicName) {
        return input.entrySet().stream()
                .filter(t -> t.getKey().equals(topicName))
                .findFirst()
                .orElse(Map.entry("", ""))
                .getValue();
    }

    public String getOutputTopic(String topicName) {
        return output.entrySet().stream()
                .filter(t -> t.getKey().equals(topicName))
                .findFirst()
                .orElse(Map.entry("", ""))
                .getValue();
    }
}
