package com.blogafac.kocirfan.services.IServices;

import com.blogafac.kocirfan.entity.User;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserServices {

    public UserDTO EntityToDto(User user);
    public User DtoToEntity(UserDTO userDTO);
    public UserDTO createUser(UserDTO userDTO);
    public List<UserDTO> getAllUsers();
    public User getUserById(Long id);
    public ResponseEntity<UserDTO> updateUser(Long id, UserDTO userDto);
    public ResponseEntity<Map<String, Boolean>> deleteUser(Long id);
}
