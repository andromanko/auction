package space.androma.auction.trades.rest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.entity.AuUser;

import java.util.Collection;

//@Component
public class AuthProvider implements AuthenticationProvider
{
/*  @Autowired
  private UserService userService;*/

    @Autowired
    IUserRepo repo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Authentication authenticate(Authentication authentication) throws AuthenticationException
  {
     //аггга, утт ищееееееемммм
     String email = authentication.getName();
     String password = (String) authentication.getCredentials();

     AuUser user = repo.findByEmail(email).orElse(null);

     if(user != null )//&& (user.getUsername().equals(username) || user.getName().equals(username)))
     {
        if(!passwordEncoder.matches(password, user.getPassword()))
        {
           throw new BadCredentialsException("Wrong password");
        }

        Collection<? extends GrantedAuthority> authorities  =  user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
     }
     else
        throw new BadCredentialsException("Username not found");
  }

  public boolean supports(Class<?> arg)
  {
     return true;
  }
}