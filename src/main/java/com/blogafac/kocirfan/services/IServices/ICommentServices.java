package com.blogafac.kocirfan.services.IServices;

import com.blogafac.kocirfan.entity.Comment;
import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.dto.CommentDTO;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICommentServices {
    //varlıktan dto ya
    public CommentDTO EntityToDto(Comment comment);

    // Dto dan varlık
    public Comment DtoToEntity(CommentDTO commentDTO);

    public List<CommentDTO> getAllComments();
    public CommentDTO createComment(CommentDTO commentDTO);
    public ResponseEntity<CommentDTO> getCommentById(Long id);
    public ResponseEntity<CommentDTO> updateComment(Long id, CommentDTO commentDTO);
    public ResponseEntity<Map<String, Boolean>> deleteComment(Long id);

}
