package com.example.agencia_de_viagem.controller;

import com.example.agencia_de_viagem.domain.dto.DestinationDTO;
import com.example.agencia_de_viagem.domain.dto.UsersDTO;
import com.example.agencia_de_viagem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gerenciar usuários do sistema.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // Injeta o serviço responsável pela lógica de negócios relacionada aos usuarios.
    @Autowired
    private UsersService usersService;

    /**
     * Cria um novo usuário.
     *
     * @param user O objeto UsersDTO contendo as informações do novo usuário.
     * @return ResponseEntity com o status HTTP 201 (Created) e o usuário criado.
     */
    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO user) {
        usersService.createUser(user);
        System.out.println("Deu certo essa merda!");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param userDTO O objeto UsersDTO contendo as informações atualizadas do usuário.
     * @return ResponseEntity com o status HTTP 200 (OK) e o usuário atualizado, ou status 404 (Not Found) caso não encontre o usuário.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable Long id, @RequestBody UsersDTO userDTO) {
        UsersDTO updatedUser = usersService.updateUser(id, userDTO);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * Retorna todos os usuarios.
     *
     * @return ResponseEntity com o status HTTP 200 (OK) e a lista de destinos.
     */
    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        // Busca todos os usuarios usando o serviço.
        List<UsersDTO> users = usersService.getAllUsers();
        // Retorna a lista de destinos com o status HTTP 200 (OK).
        System.out.println("Deu certo essa merda!");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Exclui um usuário.
     *
     * @param id O ID do usuário a ser excluído.
     * @return ResponseEntity com o status HTTP 204 (No Content) indicando a exclusão bem-sucedida.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        usersService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Recupera um usuário pelo nome de usuário.
     *
     * @param userName O nome de usuário a ser buscado.
     * @return ResponseEntity com o status HTTP 200 (OK) e o usuário encontrado, ou status 404 (Not Found) caso não encontre o usuário.
     */
    @GetMapping("/{userName}")
    public ResponseEntity<UsersDTO> getUserByUserName(@PathVariable String userName) {
        UsersDTO user = usersService.getUserByUserName(userName);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
