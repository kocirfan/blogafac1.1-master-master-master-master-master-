package com.blogafac.kocirfan.controller;

import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import com.blogafac.kocirfan.request.PostCreateRequest;
import com.blogafac.kocirfan.response.CommentResponse;
import com.blogafac.kocirfan.response.PostResponse;
import com.blogafac.kocirfan.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/v1/post")
@CrossOrigin("http://127.0.0.1:5500/")
public class PostController {

    @Autowired
    private PostService postService;

    //dto
    @PostMapping("/posts")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        postService.createPost(postDTO);
        return postDTO;
    }
    //dto
    @GetMapping("/posts")
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTO = (List<PostDTO>) postService.getAllPosts();
        return postDTO;
    }

    //dto
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        List<PostDTO> postDto = (List<PostDTO>) postService.getAllPosts();
        return ResponseEntity.ok(postDto.get(0));
    }
    //dto
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDetails){
        postService.updatePost(id, postDetails);
        return ResponseEntity.ok(postDetails);
    }

    //dto
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Long id){
       postService.deletePost(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }




////////////////////////////////////////////////////////////////////////////
    //http://localhost:8080/posts/1
//    @DeleteMapping("/{postId}")
//    public void deleteOnePost(@PathVariable Long postId) {
//        postService.deletePost(postId);
//    }
//    //http://localhost:8080/index
//    @GetMapping({"/index", "/"})
//    public String getRoot(){
//
//        return "index";
//    }

    //http://localhost:8080/posts //şimdi kapattım
//    @GetMapping
//    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId ) {
//        return postService.getAllPosts(userId);
//    }

    //http://localhost:8080/posts/1 şimdi ka
//    @GetMapping("/{postId}")
//    public Post getOnePost(@PathVariable Long postId) {
//        return postService.getPost(postId);
//    }

    //http://localhost:8080/posts
//    @PostMapping()
//    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
//        return postService.createOnePost(newPostRequest);
//    }


}
