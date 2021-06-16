package space.androma.auction.trades.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection="lot")
public class Lot {
    @Id
    private String id;
    private String description;
    private String name;
    private Long priceStart;
    private Long priceCurrent;
    private User winner;
    private Date dateTimeEnd;
    private boolean paymentDone;
}
