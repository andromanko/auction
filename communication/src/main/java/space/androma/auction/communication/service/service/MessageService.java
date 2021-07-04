package space.androma.auction.communication.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.androma.auction.communication.api.dao.IMsgDetailsRepo;
import space.androma.auction.communication.api.dao.IMsgRepo;
import space.androma.auction.communication.api.dto.MessageDto;
import space.androma.auction.communication.api.mappers.MessageMapper;
import space.androma.auction.communication.api.services.IMessageService;
import space.androma.auction.communication.api.services.ITradesConnectionService;
import space.androma.auction.communication.entity.Message;
import space.androma.auction.communication.entity.MsgDetails;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Slf4j
@Service
public class MessageService implements IMessageService {

    @Autowired
    IMsgDetailsRepo msgPermitRepo;

    @Autowired
    IMsgRepo repo;

    @Autowired
    ITradesConnectionService tradesConnectionService;

    //TODO подумать, может не void?
    //неплохо было бы проверять, а есть ли вообще такой ЛОТ? Обращение к другому сервису?
    @Override
    public boolean addMsgForLot(MessageDto messageDto) {
        MsgDetails msgDetails = msgPermitRepo.findByLotId(messageDto.getLotId()).orElse(null);
        if (msgDetails == null) {
            //спрашиваем "лот сыграл? юзеру писать можно?" //TODO - НЕКРАСИВО! повод для оптимизации!!!
            if (tradesConnectionService.UserPermitCommunicate(messageDto.getLotId(), messageDto.getUserId())) {
                //запросить credentials у trades
                msgDetails = MsgDetails.builder()
                .lotId(messageDto.getLotId())
                .sellerId(messageDto.getUserId())
                .sellerEmail("sellerEMailMustBeHere")
                .buyerId("BuyerIdMustBeHere")
                .buyerEmail("BuyerEMailMustBeHere")
                        .build();
                msgPermitRepo.save(msgDetails);
            } else {
                return false;//если ни по одному из условий не найдено
            }
        }
        //если мы здесь - значит можно коммуницировать!
            messageDto.setTime(LocalDateTime.now());
            repo.save(MessageMapper.mapMessage(messageDto));
            return true;
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
