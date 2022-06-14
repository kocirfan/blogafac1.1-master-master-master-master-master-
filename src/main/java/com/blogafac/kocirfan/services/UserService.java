package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.bean.ModelMapperBean;
import com.blogafac.kocirfan.entity.User;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import com.blogafac.kocirfan.repository.UserRepository;
import com.blogafac.kocirfan.request.UserCreateRequest;
import com.blogafac.kocirfan.services.IServices.IUserServices;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class UserService implements IUserServices {

    @Autowired
    UserRepository userRepository;


    @Autowired
    ModelMapper modelMapper;

//    public void setModelMapper(ModelMapper modelMapper) {
//        this.modelMapper.getConfiguration().setPreferNestedProperties(false);
//    }


    @Override
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = DtoToEntity(userDTO);
        userRepository.save(user);
        return userDTO;
    }


    @Override
    public UserDTO EntityToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public User DtoToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> listDto = new ArrayList<>();
        Iterable<User> userList = userRepository.findAll();
        for (User user : userList) {
            UserDTO userDTO = EntityToDto(user);
            listDto.add(userDTO);
        }
        return listDto;
    }

    @Override
    public User getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        UserDTO userDTO = EntityToDto(user);
        return user;
    }


    @Override
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDetails) {
        User user = DtoToEntity(userDetails);
        User user1 = userRepository.findById(id).orElseThrow(NullPointerException::new);
        user1.setUsername(user.getUsername());
        user1.setLastname(user.getLastname());
        user1.setPassword(user.getPassword());

        User updateUser = userRepository.save(user1);
        UserDTO userDTO = EntityToDto(updateUser);
        return ResponseEntity.ok(userDTO);

    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public User getOneUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }
}
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

//    public User createOneUser(UserCreateRequest newUserRequest) {
//        //User user = userService.getOneUserById(newUserRequest);
//
//        User toSave = new User();
//        toSave.setId(newUserRequest.getId());
//        toSave.setUserName(newUserRequest.getUserName());
//        toSave.setLastName(newUserRequest.getLastName());
//        toSave.setPassword(newUserRequest.getPassword());
//
//        return userRepository.save(toSave);
//    }
//    public void deleteById(Long userId) { şimdi kapattım
//        try {
//            userRepository.deleteById(userId);
//        } catch (EmptyResultDataAccessException e) { //user zaten yok, db'den empty result gelmiş
//            System.out.println("User " + userId + " doesn't exist"); //istersek loglayabiliriz
//        }
//    }

//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//şimdi kapattım
//    public User getOneUserById(Long userId) {
//        return userRepository.findById(userId).orElse(null);
//    }

//    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
//        User user = userRepository.findById(id).orElseThrow(NullPointerException::new);
//        UserDTO userDTO = EntityToDto(user);
//        return ResponseEntity.ok(userDTO);
//    }

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    ModelMapper modelMapper;
//
////////////////////////////////////////////////////////
//
//    @Override
//    public UserDto EntityToDto(User user) {
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//        return userDto;
//    }
//
//    @Override
//    public User DtoToEntity(UserDto userDto) {
//        User user = modelMapper.map(userDto, User.class);
//        return user;
//    }
//
//    //SAVE
//    //http://localhost:8080/users
//    @Override
//    @PostMapping("/users")
//    public UserDto createUser(@RequestBody UserDto userDto) {
//        User user = DtoToEntity(userDto);
//        userRepository.save(user);
//        return userDto;
//    }
//
//    //LIST
//    //http://localhost:8080/users
//    @GetMapping("/users")
//    @Override
//    public List<UserDto> getAllUser() {
//        List<UserDto> list = new ArrayList<>();
//        Iterable<User> listem = userRepository.findAll();
//        for (User entity : listem) {
//            UserDto userDto = EntityToDto(entity);
//            list.add(userDto);
//        }
//        return list;
//    }
//
//    //FIND
//    //http://localhost:8080/find/user/1
//    @GetMapping("/find/user/{id}")
//    @Override
//    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin " + id + " id bulamadı!!!"));
//        UserDto userDto = EntityToDto(user);
//        return ResponseEntity.ok(userDto);
//    }
//
//    //UPDATE
//    //http://localhost:8080/update/user/1
//    @Override
//    @PutMapping("/update/user/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto) {
//        User userEntity1 = DtoToEntity(userDto);
//        User entityFind = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin " + id + " id bulamadı!!!"));
//
//        entityFind.setUserName(userEntity1.getUserName());
//        entityFind.setLastName(userEntity1.getLastName());
//        entityFind.setPassword(userEntity1.getPassword());
//        entityFind.setId(userEntity1.getId());
//
//        User adminEntity2 = userRepository.save(entityFind);
//        UserDto userDto1 = EntityToDto(adminEntity2);
//        return ResponseEntity.ok(userDto1);
//    }
//
//    @Override
//    ////http://localhost:8080/delete/user/1
//    @DeleteMapping("/delete/user/{id}")
//    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable(name = "id") Long id) {
//        User entityFind = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " id bulamadı!!!"));
//        userRepository.delete(entityFind);
//
//        Map<String,Boolean> response=new HashMap<>();
//        response.put("Silindi",Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
