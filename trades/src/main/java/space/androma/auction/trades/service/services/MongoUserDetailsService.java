package space.androma.auction.trades.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.entity.User;

import java.util.Arrays;
import java.util.List;

@Component
public class MongoUserDetailsService implements UserDetailsService {
  @Autowired
  private IUserRepo repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = repository.findByUsername(username).orElse(null);

    if(user == null) {
      throw new UsernameNotFoundException("User  not found" ) ;
    }
    //TODO можно сделать через userDtoDop=) тогда не нужен будет user, будет обычный User наш
//пользователю предоставляются полномочия/роль
    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
// метод возвращает объект Spring User с username, password и role аутентифицированного пользователя.
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }
}