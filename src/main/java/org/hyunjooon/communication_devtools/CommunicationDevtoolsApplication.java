package org.hyunjooon.communication_devtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableCaching
//@EnableKafkaStreams
public class CommunicationDevtoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunicationDevtoolsApplication.class, args);
    }

}
