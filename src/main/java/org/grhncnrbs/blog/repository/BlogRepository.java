package org.grhncnrbs.blog.repository;

import org.grhncnrbs.blog.model.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Long, Blog> {

    Blog deleteByBlogId(Long blogId);

    List<Blog> findByUserId(Long blogId, Pageable pageable);

    Blog findByBlogId(Long blogId);

    Blog save(Blog blog);
}
