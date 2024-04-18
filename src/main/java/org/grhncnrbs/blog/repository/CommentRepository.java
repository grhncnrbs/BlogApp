package org.grhncnrbs.blog.repository;

import org.grhncnrbs.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment deleteByCommentId(Long commentId);

    List<Comment> findByBlogId(Long blogId);

    Comment save(Comment comment);
}
