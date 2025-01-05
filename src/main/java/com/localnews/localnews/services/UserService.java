package com.localnews.localnews.services;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.UserModel;
import com.localnews.localnews.models.exceptions.InvalidEmailException;
import com.localnews.localnews.models.exceptions.InvalidPasswordException;
import com.localnews.localnews.models.exceptions.UsernameOrEmailAlreadyExistsException;
import com.localnews.localnews.repositories.UserRepository;
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
        if (userRepository.findByUsername(userModel.getUsername()).isPresent() ||
                userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            throw new UsernameOrEmailAlreadyExistsException("Email ou Nome de Usuário já cadastrados.");
        }
        if (!EmailValidator.getInstance().isValid(userModel.getEmail())) {
            throw new InvalidEmailException("Email inválido");
        }
        if (userModel.getPassword() == null || userModel.getPassword().length() < 8) {
            throw new InvalidPasswordException("A senha deve ter no mínimo 8 caracteres.");
        }
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    // retorna usuário pelo id
    public Optional<UserModel> getUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        return userRepository.findById(id);
    }

    // atualiza usuário pelo id
    public Optional<UserModel> updateUser(Long id, UserModel userModel) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        return userRepository.findById(id)
                .map(existingUserModel -> {
                    existingUserModel.setUsername(userModel.getUsername());
                    existingUserModel.setPassword(userModel.getPassword());

                    if (userModel.getPassword() != null) {
                        existingUserModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
                    }
                    return userRepository.save(existingUserModel);
                });
    }

    // deleta usuário pelo id
    public Optional<BooleanResponseModel> deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return Optional.of(new BooleanResponseModel(true, "Usuário deletado com sucesso."));
                }).orElse(Optional.of(new BooleanResponseModel(false, "Usuário não encontrado!")));
    }
}
