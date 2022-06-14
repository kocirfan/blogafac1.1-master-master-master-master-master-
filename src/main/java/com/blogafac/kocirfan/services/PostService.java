package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.User;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import com.blogafac.kocirfan.repository.PostRepository;
import com.blogafac.kocirfan.request.PostCreateRequest;
import com.blogafac.kocirfan.response.PostResponse;
import com.blogafac.kocirfan.services.IServices.IPostServices;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PostService implements IPostServices {


    @Autowired
    ModelMapper modelMapper;


    @Autowired
    private PostRepository postRepository;
    private UserService userService;


    public PostService(PostRepository postRepository,
                       UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;

    }


    @Override
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        User user = userService.getUserById(postDTO.getUserId());

        Post post = DtoToEntity(postDTO);
        post.setUser(user);
        post.setText(postDTO.getText());
        post.setTitle(postDTO.getTitle());
        post.setId(post.getId());






//       postDTO.builder()
//               .user(user)
//                .id(postDTO.getId())
//                .text(postDTO.getText())
//                //.username(user.getUsername())
//                .userId(user.getId())
//                .title(postDTO.getTitle())
//                .build();

        postRepository.save(post);
        return postDTO;
    }


    @Override
    public PostDTO EntityToDto(Post post) {
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    @Override
    public Post DtoToEntity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        return post;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> listDTO = new ArrayList<>();
        Iterable<Post> postsList = postRepository.findAll();
        for (Post post : postsList) {
            PostDTO postDTO = EntityToDto(post);
            listDTO.add(postDTO);
        }
        return listDTO;

    }

    @Override
    public Post getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(NullPointerException::new);
        PostDTO postDTO = EntityToDto(post);
        return post;
    }

    @Override
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDetails) {
        Post post = DtoToEntity(postDetails);
        Post post1 = postRepository.findById(id).orElseThrow(NullPointerException::new);
        post1.setTitle(post.getTitle());
        post1.setText(post.getText());

        Post updatePost = postRepository.save(post1);
        PostDTO postDTO = EntityToDto(updatePost);
        return ResponseEntity.ok(postDTO);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(NullPointerException::new);
        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    public List<PostDTO> findByUserId(@PathVariable String user, @PathVariable Long userId) {
        List<PostDTO> listDTO = new ArrayList<>();
        Iterable<Post> postsList = postRepository.findByUserId(userId);
        for (Post post : postsList) {
            PostDTO postDTO = EntityToDto(post);
            listDTO.add(postDTO);
        }
        return listDTO;
    }
}
//    @Override
//    public PostDTO DtoToDto(PostDTO postDTO) {
//
//
//        if(user.getId() != null){
//            post.setId(post.getId());
//            post.setText(post.getText());
//            post.setTitle(post.getTitle());
//            post.setUser(user);
//
//            postRepository.save(post);
//        }
//        return postDTO;
//    }


//şimdi kapattım
//    public void deletePost(Long postId) {
//
//        postRepository.deleteById(postId);
//    }
//şimdi kapttım
//    public Post getPost(Long postId) {
//        return postRepository.getById(postId);
//    }
// şimd kapattım


//şimdi kapattım
//    public Post createOnePost(PostCreateRequest newPostRequest) {
//        User user = userService.getOneUserById(newPostRequest.getUserId());
//
//        Post toSave = new Post();
//        toSave.setId(newPostRequest.getId());
//        toSave.setText(newPostRequest.getText());
//        toSave.setTitle(newPostRequest.getTitle());
//        toSave.setUser(user);
//        toSave.setCreateDate(new Date());
//
//        return postRepository.save(toSave);
//    }

///    public List<PostResponse> getAllPosts(Optional<Long> userId) {
////        List<Post> list;
////        if(userId.isPresent()) {
////            list = postRepository.findByUserId(userId.get());
////        }else
////            list = postRepository.findAll();
////        return list.stream().map(p -> {
////
////            return new PostResponse(p);}).collect(Collectors.toList());
////
////    }


//    @Autowired
//    PostRepository postRepository;
//    UserService userService;
//    UserDto userDto;
//
//    @Override
//    public PostDto EntityToDto(Post post) {
//        PostDto postDto = modelMapper.map(post, PostDto.class);
//        return postDto;
//    }
//
//    @Override
//    public Post DtoToEntity(PostDto postDto) {
//        Post post = modelMapper.map(postDto, Post.class );
//        return post;
//    }
//
//
//
//    //SAVE
//    //http://localhost:8080/posts
//    @Override
//    @PostMapping("/posts")
//     public PostDto createPost(Long userId, PostDto postDto) {
//       // ResponseEntity<UserDto> user = userService.getUserById(postDto.getUserId());
//
//        Post post = DtoToEntity(postDto);
//        postRepository.save(post);
//        return postDto;
//    }
//
//// postDto.builder()
////         .id(postDto.getId())
////            .userId(postDto.getUserId())
////            .userName(postDto.getUserName())
////            .lastName(postDto.getLastName())
////            .title(postDto.getTitle())
////            .text(postDto.getText())
////            .build();
//
//    //LIST
//    //http://localhost:8080/posts
//    @GetMapping("/posts")
//    @Override
//    public List<PostDto> getAllPost() {
//       List<PostDto> list = new ArrayList<>();
//
//       Iterable<Post> listem = postRepository.findAll();
//
//       for(Post post : listem){
//           PostDto postDto = EntityToDto(post);
//           list.add(postDto);
//       }
//       return list;
//    }
//
//    //FIND
//    //http://localhost:8080/find/post/1
//    @GetMapping("/find/post/{id}")
//    @Override
//    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
//        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post" + id + "id bulunamadı"));
//        PostDto postDto = EntityToDto(post);
//        return ResponseEntity.ok(postDto);
//    }
//
//    //UPDATE
//    //http://localhost:8080/update/post/1
//    @Override
//    @PutMapping("/update/post/{id}")
//    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Long id,@RequestBody PostDto postDto) {
//        Post postEntity1 = DtoToEntity(postDto);
//        Post entityFind = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post" + id + "bulanamadı"));
//
//        entityFind.setId(postEntity1.getId());
//        entityFind.setTitle(postEntity1.getTitle());
//        entityFind.setText(postEntity1.getText());
//
//        Post postEntity2 = postRepository.save(entityFind);
//        PostDto postDto1 = EntityToDto(postEntity2);
//
//        return ResponseEntity.ok(postDto1);
//    }
//
//    @Override
//    ////http://localhost:8080/delete/post/1
//    @DeleteMapping("/delete/post/{id}")
//    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable(name = "id") Long id) {
//        Post entityFind = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post" + id + "Bulunamadı"));
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("Silindi", Boolean.TRUE);
//
//        return ResponseEntity.ok(response);
//    }
