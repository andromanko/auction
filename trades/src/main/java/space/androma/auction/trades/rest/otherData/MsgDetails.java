package space.androma.auction.trades.rest.otherData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

//табличка с данными юзеров и лотов - и мыла по которым разрешено общение
//TODO ->RestDTO
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MsgDetails {
    @NotNull
    @MongoId
    private String lotId;
    @NotNull
    private String sellerId;
    @NotNull
    private String sellerEmail;
    @NotNull
    private String buyerId;
    @NotNull
    private String buyerEmail;
}
