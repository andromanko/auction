package space.androma.auction.trades.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "user")
public class AuUser implements UserDetails {

    @Id
    private String id;
    private String username;
    private String surname;
    @NotNull
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    private Date birth;
    private Long balance;

    //лоты в которых участвовал
    private List<Lot> lotsParticipate;

    private List<Lot> lotsWins;

    private List<Lot> lotsSells;

    private String googleName;
    private String googleUsername;

/*    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)*/
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return isActive();
    }

    public boolean isActive()
    {
        return true;
    }

}
