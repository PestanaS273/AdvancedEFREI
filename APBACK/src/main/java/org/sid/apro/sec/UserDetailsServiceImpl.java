package org.sid.apro.sec;

import org.sid.apro.dao.UtilisateurRepository;
import org.sid.apro.entities.Utilisateur;

import org.sid.apro.service.IAproIniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private IAproIniService iaproInitService;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Utilisateur user = iaproInitService.getUtilisateurByEmail(mail);
        if(user == null) throw new UsernameNotFoundException(mail);
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));

        });
        return new User(user.getEmail(), user.getPassword(),authorities);
    }
}
