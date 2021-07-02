package space.androma.auction.trades.rest.config;

/*
@Component
public class AuthProvider implements AuthenticationProvider
{
  @Autowired
  private UserService userService;

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
}*/