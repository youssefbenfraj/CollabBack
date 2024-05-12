package tn.esprit.espritcollabbackend.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public User getUserFromUserDetails(UserDetails userDetails) {
        if (userDetails instanceof UserDetailsImpl) {
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;
            return userDetailsImpl.getUser();
        }
        return null;
    }
}