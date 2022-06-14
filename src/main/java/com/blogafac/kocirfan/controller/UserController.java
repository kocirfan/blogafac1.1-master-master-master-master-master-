package com.blogafac.kocirfan.controller;

import com.blogafac.kocirfan.entity.User;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import com.blogafac.kocirfan.request.UserCreateRequest;
import com.blogafac.kocirfan.response.UserResponse;
import com.blogafac.kocirfan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/v1/user")
@CrossOrigin("http://127.0.0.1:5500/")
public class UserController {

    @Autowired
    private UserService userService;
    private  UserCreateRequest userCreateRequest;


    //dto//
    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return userDTO;
    }
     //dto
    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTO = (List<UserDTO>) userService.getAllUsers();
        return userDTO;
    }

    //dto
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        List<UserDTO> userDto = (List<UserDTO>) userService.getAllUsers();
        return ResponseEntity.ok(userDto.get(0));
    }

    //dto
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDetails){
        userService.updateUser(id, userDetails);
        return ResponseEntity.ok(userDetails);
    }

    //dto
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


//   @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest newUser){
//       User user = userService.createOneUser(newUser);
//        return new ResponseEntity<>(HttpStatus.CREATED);

    //http://localhost:8080/users
//      @PostMapping()
//    public User createUser(@RequestBody UserCreateRequest newUserRequest) {
//        return userService.createOneUser(newUserRequest);
//          //return new ResponseEntity<?>(HttpStatus.CREATED);
//    }

    //http://localhost:8080/users
//    @GetMapping()
//    public List<User> getAllUsers(){
//        return userService.getAllUsers();
//    }

//    //http://localhost:8080/users/1 şimdi kapanan
//    @GetMapping("/{userId}")
//    public UserResponse getOneUser(@PathVariable Long userId) {
//        User user = userService.getOneUserById(userId);
//
//        return new UserResponse(user);
//    }
//
//    //http://localhost:8080/users/1
//    @DeleteMapping("/{userId}")
//    public void deleteOneUser(@PathVariable Long userId) {
//        userService.deleteById(userId);
//    }
}
