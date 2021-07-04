package space.androma.auction.trades.api.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PriceProposeDto {
        @NotNull
        private String lotId;
        @NotNull
        private Long amount;
        @NotNull
        private String userId;

}
