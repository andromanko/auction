package space.androma.auction.trades.rest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.androma.auction.trades.api.dao.IUserRepo;
import space.androma.auction.trades.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	IUserRepo userRepo;

/*
	@Autowired
	public PasswordEncoder passwordEncoder;
*/

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepo.findByUsername(username).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
	}

/*	public List<UserDetails> populateUserDetails()
	{
		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsList
				.add(User.withUsername("employee").password(passwordEncoder.encode("pass")).roles("USER").build());
		userDetailsList
				.add(User.withUsername("manager").password(passwordEncoder.encode("pass")).roles("USER","MANAGER").build());
		
		return userDetailsList;
	}*/

}