package space.androma.auction.communication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

//табличка с данными юзеров и лотов - и мыла по которым разрешено общение
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "lot_permit")
public class MsgPermit {
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
