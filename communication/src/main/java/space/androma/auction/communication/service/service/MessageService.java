package space.androma.auction.communication.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.androma.auction.communication.api.dao.IMsgRepo;
import space.androma.auction.communication.api.dto.MessageDto;
import space.androma.auction.communication.api.mappers.MessageMapper;
import space.androma.auction.communication.api.services.IMessageService;
import space.androma.auction.communication.entity.Message;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Slf4j
@Service
public class MessageService implements IMessageService {

    @Autowired
    IMsgRepo repo;

    //TODO подумать, может не void?
    //неплохо было бы проверять, а есть ли вообще такой ЛОТ? Обращение к другому сервису?
    @Override
    public String addMsgForLot(MessageDto messageDto) {
        messageDto.setTime(LocalDateTime.now());
        return repo.save(MessageMapper.mapMessage(messageDto)).getId();
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return  MessageMapper.mapMessageDtos(repo.findAll());
    }

    //
    @Override
    public MessageDto getMsgById(String id) {
        return MessageMapper.mapMessageDto(repo.findById(id).orElse(null));
    }

    //+
    @Override
    public List<MessageDto> getLotMessages(String lotId) {
        return  MessageMapper.mapMessageDtos(repo.findByLotId(lotId));
    }

    //TODO сделать aspect на час и отправку мыла
    @Override
    public boolean sendEmailIfNotReadedForAnHour(Long msgId) {
        return false;
    }

    //+
    @Override
    public boolean makeMessageReaded(String id) {
        Message msg = repo.findById(id).orElse(null);
        if (msg!=null) {
            msg.setReaded(TRUE);
            repo.save(msg);
            return true;
        }
        return false;
    }
}
