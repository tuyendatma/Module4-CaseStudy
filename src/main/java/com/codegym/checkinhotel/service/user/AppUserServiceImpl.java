package com.codegym.checkinhotel.service.user;

import com.codegym.checkinhotel.model.AppUser;
import com.codegym.checkinhotel.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements IAppUserService, UserDetailsService {

    @Autowired
    private IAppUserRepository userRepository;

    @Override
    public AppUser getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.getUserByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(appUser.getRole());
        UserDetails userDetails = new User(appUser.getName(), appUser.getPassword(), authorities);
        return userDetails;
    }

    @Override
    public Iterable<AppUser> listCustomer() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
