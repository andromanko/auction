package space.androma.auction.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "pay")
public class Payment {
//TODO equals pattern decorator - research
    @Id
    private String id;
    @NotNull
    private String lotId; //за который лот платим
    @NotNull
    private String userId; //кто платил
    @NotNull
    private LocalDateTime date;//TODO время/дата?
    @NotNull
    private Long sum; //может тут и не нужна сумма, она ж есть в trades, но какие же платежи без сумм?

    //TODO можно сделать для ввозможности отмены платежей
    //private boolean cancelled;
}
