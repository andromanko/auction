package space.androma.auction.trades.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection="user")
public class User {

    @Id
    private String userId;
    private String name;
    private String surname;
    private String email;
    private String  password;
    private Date birth;
    private Long balance;
    //лоты в которых участвовал
    private List<Lot> lotsParticipate;

    private List<Lot> lotsWins;

    private List<Lot> lotsSells;
}
