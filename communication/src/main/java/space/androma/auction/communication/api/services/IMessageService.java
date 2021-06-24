package space.androma.auction.communication.api.services;

import org.springframework.stereotype.Service;
import space.androma.auction.communication.api.dto.MessageDto;

import java.util.List;

@Service
public interface IMessageService {

    //создать новую мессагу в переписку по лоту
    void addMsgForLot(String userId, String lotId, String text);

    //выдать мессаги (все). pageable - TODO
    List<MessageDto> getLotMessages(String lotId);

    //послать e-mail если не прочитана
    boolean sendEmailIfNotReadedForAnHour (Long msgId);
}
