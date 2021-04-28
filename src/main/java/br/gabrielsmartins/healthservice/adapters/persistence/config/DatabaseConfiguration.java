package br.gabrielsmartins.healthservice.adapters.persistence.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableR2dbcRepositories
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


    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }


}