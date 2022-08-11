package com.example.market.controller;


import com.example.market.model.entity.User;
import com.example.market.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



@Tag(name = "UserController", description = "Управление пользователями магазина")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    private final String REGISTRATION = "/registration";

    private final String DELETE_USER="/deleteUser";

    private final String GET_ALL_USERS="/getAll";

    @Operation(summary = "Регистрация пользователя",description = "Создает нового пользователя с ролью USER")
    @PreAuthorize("permitAll()")
    @PostMapping(REGISTRATION)
    public ResponseEntity createNewUser(@Parameter(description = "Объект класса User, содержит данные пользователя") @RequestBody User user){
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.createNewUser(user));
    }

    @Operation(summary = "Удаление пользователя",description = "Удаление пользователем своей учетной записи по данным из Principal, т.е. для авторизированных пользователей")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(DELETE_USER)
    public ResponseEntity deleteUserById(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user){
        return ResponseEntity.ok(userService.deleteById(user));
    }

    @Hidden
    @Operation(summary = "Получение списка пользователей",description = "Получение списка всех пользователей, доступно для пользователей с ролью ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(GET_ALL_USERS)
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
