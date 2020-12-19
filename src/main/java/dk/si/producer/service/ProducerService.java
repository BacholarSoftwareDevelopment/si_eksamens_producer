package dk.si.producer.service;

import dk.si.producer.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);


    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendObject(Message message) {
        template.send(message.getTopic(), new Message(
                message.getId(),
                message.getTopic(),
                message.getName(),
                message.getCity(),
                message.getMessage()));
        logger.info("### Producer sends customer [{}:{}:{}:{}:{}]",
                message.getId(),
                message.getTopic(),
                message.getName(),
                message.getCity(),
                message.getMessage());
        template.flush();
    }

//    public boolean isHotel(Message message){
//        return message.getTopic().equals("HOTEL");
//    }
//
//    public boolean isTourism(Message message){
//        return message.getTopic().equals("TOURISM");
//    }
//
//    public boolean isAriport(Message message){
//        return message.getTopic().equals("AIRPORT");
//    }
}

