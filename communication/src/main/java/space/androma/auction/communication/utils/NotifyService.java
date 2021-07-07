package space.androma.auction.communication.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.androma.auction.communication.api.dao.IMsgDetailsRepo;
import space.androma.auction.communication.api.dao.IMsgRepo;
import space.androma.auction.communication.api.utils.IEmailSender;
import space.androma.auction.communication.api.utils.INotifyService;
import space.androma.auction.communication.entity.Message;
import space.androma.auction.communication.entity.MsgDetails;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class NotifyService implements INotifyService {

    @Autowired
    IMsgRepo msgRepo;

    @Autowired
    IMsgDetailsRepo msgPermitRepo;

    @Autowired
    IEmailSender emailSender;

    @Override
    public void control() throws Exception {
//TODO переделать условия!!! переделать поиск мыла по юзеру! ошибка в ДНК налицо -(
        List<Message> allMessages = msgRepo.findAll();
        for (Message msg: allMessages) {
            if ((msg.isReaded())||(msg.isNotifySent())) {continue;}
            if (msg.getTime().plusHours(1L).isAfter(LocalDateTime.now())) {continue;}
            //msg.getUserId() - от этого юзера вышла мессага, на которую нужно сделать нотификашку оппоненту про лот msg.getLotId()
            //ищем
            //если до сюда дошли - группируем по Юзеру отправляем Юзеру "у ВАс есть непрочитанные мессаги"
            MsgDetails msgDet = msgPermitRepo.findByLotId(msg.getLotId()).orElse(null);
            if (msgDet==null) {continue;} //не должно быть такого, но вдруг...
            String email=null; //ну или проинициализировать мылом админа =)
            if (msgDet.getSellerId().equals(msg.getUserId())) {email = msgDet.getBuyerEmail();}
            if (msgDet.getBuyerId().equals(msg.getUserId())) {email = msgDet.getSellerEmail();}
            if (email!=null) {
                msg.setNotifySent(true);
                msgRepo.save(msg);
                emailSender.sendEmailToUser(email,1);
                log.info("email sent to:"+email);
            }
        }
    }
}
