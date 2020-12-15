package dk.si.producer.resource;

import dk.si.producer.model.Message;

import dk.si.producer.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class MessageResource {

    private static Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @Value("${spring.kafka.producer.topic}")
    String topic;

    @Autowired
    private ProducerService service;

    @PostMapping("/message")
    public String sendMyMessageToKafka(@RequestBody Message message)
    {
        service.sendObject(message);
        logger.info("### Producer sends customer [{}:{}:{}:{}:{}]",
                message.getId(),
                message.getName(),
                message.getCity(),
                message.getMessage());

        return "Message published: " + message;
    }
}
