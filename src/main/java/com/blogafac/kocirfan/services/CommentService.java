package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.entity.Comment;
import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.User;
import com.blogafac.kocirfan.entity.dto.CommentDTO;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import com.blogafac.kocirfan.repository.CommentRepository;
import com.blogafac.kocirfan.request.CommentCreateRequest;
import com.blogafac.kocirfan.request.CommentUpdateRequest;
import com.blogafac.kocirfan.response.CommentResponse;
import com.blogafac.kocirfan.services.IServices.ICommentServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentServices {

    @Autowired
    ModelMapper modelMapper;

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;



    public CommentService(CommentRepository commentRepository, UserService userService,
                          PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;


    }


    @Override
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
        User user = userService.getUserById(commentDTO.getUserId());
        Post post = postService.getPostById(commentDTO.getPostId());
        Comment comment = DtoToEntity(commentDTO);
        comment.setUser(user);
        comment.setPost(post);
        comment.setId(comment.getId());
        comment.setText(comment.getText());
        comment.setDurum(comment.getDurum());

//        CommentDTO.builder()
//                .id(commentDTO.getId())
//                .text(commentDTO.getText())
//                .user(commentDTO.getUser())
//                .post(commentDTO.getPost())
//                .username(commentDTO.getUsername())
//                .build();


        commentRepository.save(comment);


        return commentDTO;
    }

    @Override
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(NullPointerException::new);
        CommentDTO commentDTO = EntityToDto(comment);
        return ResponseEntity.ok(commentDTO);
    }

    @Override
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDetails) {
        Comment comment = DtoToEntity(commentDetails);
        Comment comment1 = commentRepository.findById(id).orElseThrow(NullPointerException::new);
        comment1.setText(comment.getText());
        comment1.setDurum(comment.getDurum());
        Comment commentUpdate = commentRepository.save(comment1);
        CommentDTO commentDTO = EntityToDto(commentUpdate);
        return ResponseEntity.ok(commentDTO);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(NullPointerException::new);
        commentRepository.delete(comment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    @Override
    public CommentDTO EntityToDto(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        return commentDTO;
    }

    @Override
    public Comment DtoToEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return comment;
    }

    @Override
    public List<CommentDTO> getAllComments() {

        List<CommentDTO> listDTO = new ArrayList<>();
        Iterable<Comment> commentList = commentRepository.findAll();
        for (Comment comment : commentList) {
            CommentDTO commentDTO = EntityToDto(comment);
            listDTO.add(commentDTO);
        }
        return listDTO;
    }











    //getcomment şimdi
//    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
//        List<Comment> comments;
//        if (userId.isPresent() && postId.isPresent()) {
//            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
//        } else if (userId.isPresent()) {
//            comments = commentRepository.findByUserId(userId.get());
//        } else if (postId.isPresent()) {
//            comments = commentRepository.findByPostId(postId.get());
//        } else
//            comments = commentRepository.findAll();
//        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
//    }


    //create comment şimdi
//    public Comment createOneComment(CommentCreateRequest request) {
//        User user = userService.getOneUserById(request.getUserId());
//        Post post = postService.getPost(request.getPostId());
//        if (user != null && post != null) {
//            Comment commentToSave = new Comment();
//            commentToSave.setId(request.getId());
//            commentToSave.setPost(post);
//            commentToSave.setDurum(request.getDurum());
//            commentToSave.setUser(user);
//            commentToSave.setText(request.getText());
//            commentToSave.setCreateDate(new Date());
//            return commentRepository.save(commentToSave);
//        } else
//            return null;
//
//
//    }

    //update comment şimdi kapttım
//    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
//        Optional<Comment> comment = commentRepository.findById(commentId);
//        if (comment.isPresent()) {
//            Comment commentToUpdate = comment.get();
//            // commentToUpdate.setText(request.getText());
//            commentToUpdate.setDurum(request.getDurum());
//            return commentRepository.save(commentToUpdate);
//        } else
//            return null;
//    }

    //delete comment şimdi kapatılan 2
//    public void deleteOneCommentById(Long commentId) {
//        commentRepository.deleteById(commentId);
//    }
//
//    //one comment
//    public Comment getOneCommentById(Long commentId) {
//        return commentRepository.findById(commentId).orElse(null);
//    }

    //duruma göre listele
//    public List<Comment> getByDurum( @PathVariable String durum) {
//        List<CommentResponse> comments = CommentResponse
//
//        if( comments.getDurum() != 0){
//            comments = commentRepository.getByDurum(durum);
//        }else{
//            comments = commentRepository.findAll();
//        }
//
//        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
//    }


}