package com.roovies.restapi.domain.user.service;

import com.roovies.restapi.domain.user.dto.UserDTO;
import com.roovies.restapi.domain.user.entity.User;
import com.roovies.restapi.domain.user.repository.UserRepository;
import com.roovies.restapi.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserMapper userMapper;
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPwd(userDTO.getPwd());
        user.setName(userDTO.getName());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getPwd(), savedUser.getName());
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = userMapper.usersTouserDTOs(users);
        return userDTOs;
//        return users.stream().map(user->modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        Optional<User> founduser = userRepository.findById(id);
        UserDTO founduserDTO = new UserDTO();
        if(founduser.isPresent()){
            founduserDTO = userMapper.userToUserDTO(founduser.get());
            return founduserDTO;
        }
        else{
            return founduserDTO;
        }
    }

    public int deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return 1;
        } catch(EmptyResultDataAccessException e){
            return 0;
        }
    }
}
