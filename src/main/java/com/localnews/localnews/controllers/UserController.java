package com.localnews.localnews.controllers;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.UserModel;
import com.localnews.localnews.models.exceptions.InvalidEmailException;
import com.localnews.localnews.models.exceptions.InvalidPasswordException;
import com.localnews.localnews.models.exceptions.UserNotFoundException;
import com.localnews.localnews.models.exceptions.UsernameOrEmailAlreadyExistsException;
import com.localnews.localnews.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // cria usuário
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserModel userModel) {
        try {
            UserModel createdUserModel = userService.createUser(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BooleanResponseModel(true,
                    "Usuário criado com sucesso."));
        }
        catch (UsernameOrEmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BooleanResponseModel(false,e.getMessage()));
        }
        catch (InvalidEmailException | InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BooleanResponseModel(false, e.getMessage()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }

    // retorna um usuário pelo id
    @GetMapping("/info/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // atualiza um usuário pelo id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Validated @RequestBody UserModel userModel) {
        try {
            userService.updateUser(id, userModel);
            return ResponseEntity.ok(new BooleanResponseModel(true, "Usuário atualizado com sucesso."));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BooleanResponseModel(false, e.getMessage()));
        } catch (InvalidEmailException | InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno.");

        }
    }

    // deleta um usuário pelo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BooleanResponseModel> deleteUser(@PathVariable Long id) {
        Optional<BooleanResponseModel> response = userService.deleteUser(id);
        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
