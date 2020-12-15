package dk.si.producer.resource;

import dk.si.producer.model.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class MessageResource {

    private static Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @Autowired
    private KafkaTemplate<String, Message> template; // key = TOPIC value = Mail object

    @PostMapping("/message")
    public String sendMyMessageToKafka(@RequestBody Message message)
    {
        System.out.printf(message.getTopic() + " " + message);

        template.send(message.getTopic(), message.getKey(), message);
        logger.info("### Producer sends message [{}] with topic [{}]", message.getMessage(), message.getTopic());
        template.flush();

        return "Message published: " + message.getMessage() + " with Topic: " + message.getTopic();
    }
}
