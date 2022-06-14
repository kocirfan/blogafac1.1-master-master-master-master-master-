package com.blogafac.kocirfan.services.IServices;

import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IPostServices {

    //varlıktan dto ya
    public PostDTO EntityToDto(Post post);

    // Dto dan varlık
    public Post DtoToEntity(PostDTO postDTO);
    public PostDTO createPost(PostDTO postDTO);
    //public  PostDTO DtoToDto(PostDTO postDTO, UserDTO userDTO);
    public List<PostDTO> getAllPosts();
    public Post getPostById(Long id);
    public ResponseEntity<PostDTO> updatePost(Long id, PostDTO postDto);
    public ResponseEntity<Map<String, Boolean>> deletePost(Long id);
    public  List<PostDTO> findByUserId(String user,Long userId);
}
