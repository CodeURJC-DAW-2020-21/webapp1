package es.codeurjc.friends_padel_tour.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.codeurjc.friends_padel_tour.Entities.User;
import es.codeurjc.friends_padel_tour.Repositories.UserRepository;



@Service
public class UserService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEnconder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + role));
		}

		return new org.springframework.security.core.userdetails.User(user.getName(), 
				user.getEncodedPassword(), roles);

	}

	public User saveUser(String username, String password) {
		User newUser =  new User(username,passwordEnconder.encode(password),"USER");
		userRepository.save(newUser);
		return newUser;
	}

}
