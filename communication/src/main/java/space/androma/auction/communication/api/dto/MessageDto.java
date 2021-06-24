package space.androma.auction.communication.api.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

    private String id;
    private String userId;
    private String lotId;
    private String text;
    private LocalDateTime time;
    private boolean readed;
}

