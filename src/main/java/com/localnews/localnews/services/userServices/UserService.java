package com.localnews.localnews.services.userServices;

import com.localnews.localnews.models.userModels.UserDTO;
import com.localnews.localnews.models.userModels.UserModel;
import com.localnews.localnews.models.usersExceptions.*;
import com.localnews.localnews.repositories.userRepositories.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // cria usuário validando informações repassadas
    public UserModel createUser(UserModel userModel) {
        if (userRepository.findByUsername(userModel.getUsername()).isPresent()) {
            throw new UsernamelAlreadyExistsException("Nome de Usuário já cadastrado.");
        }
        if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("E-mail já cadastrado");
        }
        if (!EmailValidator.getInstance().isValid(userModel.getEmail())) {
            throw new InvalidEmailException("Email inválido");
        }
        if (userModel.getPassword() == null || userModel.getPassword().length() < 8) {
            throw new InvalidPasswordException("A senha deve ter no mínimo 8 caracteres.");
        }
        if (userModel.getUsername() == null || userModel.getUsername().length() < 8) {
            throw new InvalidUsernameException("O nome de usuário deve ter no mínimo 8 caracteres.");
        }
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    // retorna pelo ido usando o DTO
    public Optional<UserDTO> getUserById(Long id) {
        if (userRepository.findUsernameAndEmailById(id).isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        }
        return userRepository.findUsernameAndEmailById(id);
    }

    // atualiza usuário pelo id
    public void updateUser(Long id, UserModel userModel) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        }
        userRepository.findById(id)
                .map(existingUserModel -> {
                    if (userModel.getUsername() == null && userModel.getUsername().length() < 8) {
                        throw new InvalidUsernameException("O nome de usuário deve ter no mínimo 8 caracteres.");
                    }
                    if (userModel.getPassword() == null && userModel.getPassword().length() < 8) {
                        throw new InvalidPasswordException("A senha deve ter no mínimo 8 caracteres.");
                    }

                    existingUserModel.setUsername(userModel.getUsername());
                    existingUserModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
                    return userRepository.save(existingUserModel);
                });
    }

    // deleta usuário pelo id
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty() || !userRepository.existsById(id)) {
            throw new UserNotFoundException("Usuário nao encontrado.");
        }
        userRepository.deleteById(id);
    }
}
