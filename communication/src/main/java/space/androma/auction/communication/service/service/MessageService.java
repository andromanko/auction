package space.androma.auction.communication.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import space.androma.auction.communication.api.dao.IMsgRepo;
import space.androma.auction.communication.api.dto.MessageDto;
import space.androma.auction.communication.api.mappers.MessageMapper;
import space.androma.auction.communication.api.services.IMessageService;
import space.androma.auction.communication.entity.Message;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService implements IMessageService {

    @Autowired
    IMsgRepo repo;

    //TODO подумать, может не void?
    @Override
    public void addMsgForLot(String userId, String lotId, String text) {
        repo.save(Message.builder()
                .lotId(lotId)
                .userId(userId)
                .text(text)
                .time(LocalDateTime.now())
                .build());
    }

    @Override
    public List<MessageDto> getLotMessages(String lotId) {
        return  MessageMapper.mapMessageDtos(repo.findByLotId(lotId));
    }

    //TODO сделать aspect на час и отправку мыла
    @Override
    public boolean sendEmailIfNotReadedForAnHour(Long msgId) {
        return false;
    }
}
