package es.codeurjc.friends_padel_tour.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	//Autowired section
	@Autowired
	private PasswordEncoder passwordEnconder;

	@Autowired
	private UserRepository userRepository;

	//Load a user accordin to his username having in mind his role ant the password
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + role));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getEncodedPassword(), roles);

	}

	//Save a user 
	public User saveUser(String username, String password, String role) {
		User newUser =  new User(username,passwordEnconder.encode(password), role);
		userRepository.save(newUser);
		return newUser;
	}

	//Find a user
	public User findByUsername(String name) {
		Optional<User> userInDB = userRepository.findByUsername(name);
		if(userInDB.isPresent())
			return userInDB.get();
		else
			return null;
	}

    public void updatePasswordOf(User user, String password) {
		user.setEncodedPassword(passwordEnconder.encode(password));
    }
	

}
