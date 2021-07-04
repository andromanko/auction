package space.androma.auction.communication.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "msg")
public class Message {

    @Id
    private String id;
    @NotNull
    private String lotId; //к которому лоту относится переписка
    @NotNull
    private String userId; //нам всё равно кто продаван/кто покупатель/даже админ может вмешаться в переписку ))
    private String text;
    private LocalDateTime time;
    @Builder.Default
    private boolean readed=FALSE;
    @Builder.Default
    private boolean notifySent=FALSE;
}
