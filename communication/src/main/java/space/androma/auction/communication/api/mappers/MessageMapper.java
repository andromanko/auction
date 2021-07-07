package space.androma.auction.communication.api.mappers;

import lombok.experimental.UtilityClass;
import space.androma.auction.communication.api.dto.MessageDto;
import space.androma.auction.communication.entity.Message;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MessageMapper {

    public Message mapMessage (MessageDto source) {
        return Message.builder()
                .id(source.getId())
                .userId(source.getUserId())
                .lotId(source.getLotId())
                .text(source.getText())
                .time(source.getTime())
                .build();
    }

    public MessageDto mapMessageDto(Message source) {
        return MessageDto.builder()
                .id(source.getId())
                .userId(source.getUserId())
                .lotId(source.getLotId())
                .time(source.getTime())
                .text(source.getText())
                .build();
    }


    public List<Message> mapMessage(List<MessageDto> source) {
        return source.stream().map(MessageMapper::mapMessage).collect(Collectors.toList());
    }

    public List<MessageDto> mapMessageDtos(List<Message> source) {
        return source.stream().map(MessageMapper::mapMessageDto).collect(Collectors.toList());
    }
}
