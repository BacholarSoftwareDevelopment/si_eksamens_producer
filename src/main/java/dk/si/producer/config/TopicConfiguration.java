package dk.si.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration
{
      @Value("${spring.kafka.producer.topic}")
      String topic;

      @Bean
      public NewTopic topicFactory()
      {
            return TopicBuilder.name(topic)
                    .partitions(3)
                    .replicas(1)
                    .build();
      }
}
