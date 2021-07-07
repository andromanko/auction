package space.androma.auction.communication.utils;

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
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepo.findByUsername(username).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
	}

}