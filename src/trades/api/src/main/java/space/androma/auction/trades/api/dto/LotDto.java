package space.androma.auction.trades.api.dto;

import lombok.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotDto {

	private Long lotId;

	private String description;
	private String name;
	private Long priceStart;
	private Long priceCurrent;
	private UserDto winner;
	private Date dateTimeEnd;
	private boolean paymentDone;

}

