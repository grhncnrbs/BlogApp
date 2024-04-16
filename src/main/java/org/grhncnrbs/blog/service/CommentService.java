package org.grhncnrbs.blog.service;

import org.grhncnrbs.blog.model.Comment;
import org.grhncnrbs.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) throws Exception{
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment)throws Exception{
        return commentRepository.save(comment);
    }

    public Comment deleteComment(Long commentId) throws Exception {
        return commentRepository.deleteByCommentId(commentId);
    }

    public List<Comment> getComment(Long commentId, Pageable pageable) throws Exception {
        return commentRepository.findByBlogId(commentId, pageable);
    }

    public List<Comment> getBlogs(Long blogId, Pageable pageable) throws Exception {
        return commentRepository.findByBlogId(blogId, pageable);
    }
}
