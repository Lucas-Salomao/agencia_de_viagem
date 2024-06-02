package com.example.agencia_de_viagem.config;

import com.example.agencia_de_viagem.domain.entity.UserEntity;
import com.example.agencia_de_viagem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUserName(username);
        return userEntity.map(AuthenticatedUser::new).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }

}
