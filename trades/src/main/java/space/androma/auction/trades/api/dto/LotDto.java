package space.androma.auction.trades.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotDto {

    private String id;

    private String description;
    private String name;
    private Long priceStart;
    private Long priceCurrent;
    private String winner;
    private LocalDateTime dateTimeEnd;
    private boolean paymentDone;

}

