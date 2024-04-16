package org.grhncnrbs.blog.service;

import org.grhncnrbs.blog.model.Blog;
import org.grhncnrbs.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(Blog blog) throws Exception{
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Blog blog)throws Exception{
        return blogRepository.save(blog);
    }

    public Blog deleteBlog(Long blogId) throws Exception {
        return blogRepository.deleteByBlogId(blogId);
    }

    public Blog getBlog(Long blogId, Pageable pageable) throws Exception {
        return blogRepository.findByBlogId(blogId, pageable);
    }

    public List<Blog> getBlogs(Long userId, Pageable pageable) throws Exception {
        return blogRepository.findByUserId(userId, pageable);
    }
}
