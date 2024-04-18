package org.grhncnrbs.blog.service;

import org.grhncnrbs.blog.dto.CommonPaginationRequest;
import org.grhncnrbs.blog.dto.CreateBlogRequest;
import org.grhncnrbs.blog.dto.UpdateBlogRequest;
import org.grhncnrbs.blog.model.Blog;
import org.grhncnrbs.blog.repository.BlogRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(CreateBlogRequest createBlogRequest) throws Exception{
        Blog blog = new Blog();
        BeanUtils.copyProperties(createBlogRequest,blog);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    public Blog updateBlog(UpdateBlogRequest updateBlogRequest)throws Exception{
        Blog blog = blogRepository.findByBlogId(updateBlogRequest.getBlogId());
        BeanUtils.copyProperties(updateBlogRequest,blog);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    public Blog deleteBlog(Long blogId) throws Exception {
        return blogRepository.deleteByBlogId(blogId);
    }

    public Blog getBlog(Long blogId) throws Exception {
        return blogRepository.findByBlogId(blogId);
    }

    public List<Blog> getBlogs(CommonPaginationRequest commonPaginationRequest) throws Exception {
        Pageable pageable = PageRequest.of(commonPaginationRequest.getPageNo(), commonPaginationRequest.getPageSize(),
                Sort.by(commonPaginationRequest.getSortBy()).descending());
        return blogRepository.findByUserId(commonPaginationRequest.getValue(), pageable);
    }
}
