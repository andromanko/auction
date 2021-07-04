package space.androma.auction.communication.api.services;

import org.springframework.stereotype.Service;
import space.androma.auction.communication.api.dto.MessageDto;

import java.util.List;

@Service
public interface IMessageService {

    //создать новую мессагу в переписку по лоту
    boolean addMsgForLot(MessageDto messageDto);

    //выдать мессаги (все). pageable - TODO
    List<MessageDto> getLotMessages(String lotId);

    List<MessageDto> getAllMessages();

    MessageDto getMsgById(String id);
    //послать e-mail если не прочитана
    boolean sendEmailIfNotReadedForAnHour (Long msgId);

    boolean makeMessageReaded (String id);
}
