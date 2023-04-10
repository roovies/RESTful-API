package com.roovies.restapi.domain.user.controller;

import com.roovies.restapi.domain.user.dto.UserDTO;
import com.roovies.restapi.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restapi")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getEmail());
        System.out.println(userDTO.getPwd());
        System.out.println(userDTO.getName());
        UserDTO savedUser = userService.saveUser(userDTO);
        return savedUser;
    }

    @GetMapping("/users")
    public List<UserDTO> showUsers(){
        List<UserDTO> users = userService.findAll();
        return users;
    }

    @GetMapping("/user/{id}")
    public UserDTO showUser(@PathVariable Long id){
        UserDTO user = userService.findById(id);
        return user;
    }

    @PatchMapping("/modify/{id}")
    public UserDTO modifyUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        userDTO.setId(id);
        UserDTO modifiedUser = userService.saveUser(userDTO);
        return modifiedUser;
    }

    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable Long id){
        int answer = userService.deleteUser(id);
        if(answer >0){
            return "삭제 성공";
        }
        else{
            return "삭제 실패(존재하지 않는 회원)";
        }
    }
}
