package com.example.agencia_de_viagem.service;

import com.example.agencia_de_viagem.domain.dto.UsersDTO;
import com.example.agencia_de_viagem.domain.entity.UserEntity;
import com.example.agencia_de_viagem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public UsersDTO createUser(UsersDTO userDTO) {
        UserEntity userEntity = convertToEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return convertToDTO(userEntity);
    }

    /**
     * Retorna todos os usuarios cadastrados.
     *
     * @return Uma lista com todos os usuarios.
     */
    public List<UsersDTO> getAllUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();
        return usersEntities.stream()
                .map(this::convertToDTO) // Utiliza a função toDTO para converter a lista de entidades para DTOs
                .collect(Collectors.toList());
    }

    public UsersDTO updateUser(Long id, UsersDTO userDTO) {
        UserEntity userEntity = userRepository.getReferenceById(id);
        if(userEntity == null) {
            return null;
        }

        userEntity.setUserName(userDTO.getUserName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity = userRepository.save(userEntity);
        return convertToDTO(userEntity);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UsersDTO getUserByUserName(String userName) {
        Optional<UserEntity> userEntity = userRepository.findByUserName(userName);
        return userEntity.map(this::convertToDTO).orElse(null);
    }

    private UserEntity convertToEntity(UsersDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setPassword(userDTO.getPassword()); // Senha não criptografada
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }

    private UsersDTO convertToDTO(UserEntity userEntity) {
        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setPassword(userEntity.getPassword()); // Senha não criptografada
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }
}
