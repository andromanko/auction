package space.androma.auction.payment.api.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private String id;
    private String userId;
    private String lotId;
    private Long sum;
}

