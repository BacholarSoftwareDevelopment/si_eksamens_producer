package dk.si.producer.service;

import dk.si.producer.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Value("${spring.kafka.producer.topic}")
    String topic;
    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendObject(Message message)
    {
        template.send(topic, new Message(
                message.getId(),
                message.getName(),
                message.getCity(),
                message.getMessage()));
        logger.info("### Producer sends customer [{}:{}:{}:{}:{}]",
                message.getId(),
                message.getName(),
                message.getCity(),
                message.getMessage());
        template.flush();
    }
}
