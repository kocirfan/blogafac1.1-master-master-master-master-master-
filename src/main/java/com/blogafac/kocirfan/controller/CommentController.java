package com.blogafac.kocirfan.controller;

import com.blogafac.kocirfan.entity.Comment;
import com.blogafac.kocirfan.entity.dto.CommentDTO;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import com.blogafac.kocirfan.request.CommentCreateRequest;
import com.blogafac.kocirfan.request.CommentUpdateRequest;
import com.blogafac.kocirfan.response.CommentResponse;
import com.blogafac.kocirfan.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/v1/comment")
@CrossOrigin("http://127.0.0.1:5500/")
public class CommentController {

    @Autowired
    private CommentService commentService;


    //dto//
    @PostMapping("/comments")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO){
        commentService.createComment(commentDTO);
        return commentDTO;
    }
    //dto//
    @GetMapping("/comments")
    public List<CommentDTO> getAllComments(){
        List<CommentDTO> commentDTO = commentService.getAllComments();
        return commentDTO;
    }
    //dto
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id){
        List<CommentDTO> commentDto = (List<CommentDTO>) commentService.getAllComments();
        return ResponseEntity.ok(commentDto.get(0));
    }

    //dto
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDetails){
        commentService.updateComment(id, commentDetails);
        return ResponseEntity.ok(commentDetails);
    }

    //dto
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }

    //get comment list
    //http://localhost:8080/commnets   şimdi kapattım
//    @GetMapping("/commnets")
//    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId,
//                                                @RequestParam Optional<Long> postId) {
//        return commentService.getAllCommentsWithParam(userId, postId);
//    }



    //create comment
    //http://localhost:8080/comments
//    @PostMapping("/commnets")
//    public Comment createOneComment(@RequestBody CommentCreateRequest request) {
//        return commentService.createOneComment(request);
//    }
    //update comment
    //http://localhost:8080/commnets/ şimdi ka
//    @PutMapping("/{commentId}")
//    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
//        return commentService.updateOneCommentById(commentId, request);
//    }

    //delete comment şimdi ka
    //http://localhost:8080/commnets/1
//    @DeleteMapping("/{commentId}")
//    public void deleteOneComment(@PathVariable Long commentId) {
//        commentService.deleteOneCommentById(commentId);
//    }
//
//    //getby
//    //http://localhost:8080/commnets/1
//    @GetMapping("/{commentId}")
//    public Comment getOneComment(@PathVariable Long commentId) {
//        return commentService.getOneCommentById(commentId);
//    }

//     duruma göre listele
//    @GetMapping("/durum")
//    public List<CommentResponse> getByDurum(String durum){
//        return commentService.getByDurum(durum);
//    }


}
