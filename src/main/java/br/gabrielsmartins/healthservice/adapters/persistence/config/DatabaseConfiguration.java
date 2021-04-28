package br.gabrielsmartins.healthservice.adapters.persistence.config;

import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter.GenderDataReadConverter;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter.GenderDataWriteConverter;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter.MeasurementTypeDataReadConverter;
import br.gabrielsmartins.healthservice.adapters.persistence.entity.enums.converter.MeasurementTypeDataWriteConverter;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.binding.BindMarkersFactory;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    private final R2dbcProperties properties;

    @Override
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions.Builder builder = ConnectionFactoryOptions.parse(properties.getUrl()).mutate();
        String username = properties.getUsername();
        if (StringUtils.hasText(username)) {
            builder.option(ConnectionFactoryOptions.USER, username);
        }
        String password = properties.getPassword();
        if (StringUtils.hasText(password)) {
            builder.option(ConnectionFactoryOptions.PASSWORD, password);
        }
        String databaseName = properties.getName();
        if (StringUtils.hasText(databaseName)) {
            builder.option(ConnectionFactoryOptions.DATABASE, databaseName);
        }
        if (properties.getProperties() != null) {
            properties.getProperties()
                    .forEach((key, value) -> builder
                            .option(Option.valueOf(key), value));
        }
        return ConnectionFactories.get(builder.build());
    }

    @Override
    protected List<Object> getCustomConverters() {
        List<Object> converterList = new ArrayList<>();
        converterList.add(new GenderDataReadConverter());
        converterList.add(new GenderDataWriteConverter());
        converterList.add(new MeasurementTypeDataReadConverter());
        converterList.add(new MeasurementTypeDataWriteConverter());
        return converterList;
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .bindMarkers(() -> BindMarkersFactory.named(":", "", 256).create())
                .namedParameters(true)
                .build();
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

}