package dk.si.producer.resource;

import dk.si.producer.model.Message;

import dk.si.producer.service.ProducerService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kafka")
public class MessageResource {

    private static Logger logger = LoggerFactory.getLogger(MessageResource.class);

    @Autowired
    private ProducerService service;

    @PostMapping("/message")
    public Message sendMyMessageToKafka(@RequestBody Message message) {
        service.sendObject(message);
        logger.info("### Producer sends customer [{}:{}:{}:{}:{}]",
                message.getId(),
                message.getTopic(),
                message.getName(),
                message.getCity(),
                message.getMessage());

        return message;
    }
}
