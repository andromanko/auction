package space.androma.auction.communication.rest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.androma.auction.communication.api.dto.MessageDto;
import space.androma.auction.communication.api.services.IMessageService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    IMessageService messageService;

    //прочитать всё //юзера пока не проверяем
    @GetMapping()
    public List<MessageDto> getAllMessages()
    {
        return messageService.getAllMessages();
    }

//прочитать касаемо ЛОТа //юзера пока не проверяем
    @GetMapping(value = "/lot/{lotId}")
    public List<MessageDto> getLotMessages(@PathVariable String lotId)
    {
        return messageService.getLotMessages(lotId);
    }

    //запостить касаемо ЛОТа //тут юзера надо проверить
    @PostMapping(value = "/add")
    public boolean addMsg( @RequestBody  MessageDto messageDto) {

       return messageService.addMsgForLot(messageDto);
    }


    @GetMapping(value = "/{id}") //юзера пока не проверяем
    public MessageDto getMsgInfo(@PathVariable String id)
    {
        return messageService.getMsgById(id);
    }

    @PatchMapping(value="/{id}")
    public boolean setMsgReaded(@PathVariable String id) {
        return messageService.makeMessageReaded(id);
    }
    //TODO PAGEABLE? GET ALL messages
}
