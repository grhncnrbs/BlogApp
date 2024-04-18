package org.grhncnrbs.blog.controller;

import jakarta.validation.Valid;
import org.grhncnrbs.blog.dto.CommonPaginationRequest;
import org.grhncnrbs.blog.dto.CreateBlogRequest;
import org.grhncnrbs.blog.dto.DbsResponseEntity;
import org.grhncnrbs.blog.dto.UpdateBlogRequest;
import org.grhncnrbs.blog.model.Blog;
import org.grhncnrbs.blog.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("v1/blogs")
    public ResponseEntity<DbsResponseEntity> createBlogCall(@Valid @RequestBody CreateBlogRequest createBlogRequest) {
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
        try {
            Blog createdBlog = blogService.createBlog(createBlogRequest);
            dbsResponseEntity.setMessage("Blog created successfully.");
            dbsResponseEntity.setData(createdBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("v1/blogs")
    public ResponseEntity<DbsResponseEntity> updateBlogCall(@Valid @RequestBody UpdateBlogRequest updateBlogRequest){
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
        try {
            Blog updatedBlog = blogService.updateBlog(updateBlogRequest);
            dbsResponseEntity.setMessage("Blog updated successfully.");
            dbsResponseEntity.setData(updatedBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("v1/blogs/{blogId}")
    public ResponseEntity<DbsResponseEntity> getBlogCall(@PathVariable Long blogId){
        Blog blog = new Blog();
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
        try {
            Blog gettedBlog = blogService.getBlog(blogId);
            dbsResponseEntity.setMessage("Blogs getted successfully.");
            dbsResponseEntity.setData(gettedBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("v1/blogs/{blogId}")
    public ResponseEntity<DbsResponseEntity> deleteBlogCall(@PathVariable Long blogId){
        Blog blog = new Blog();
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
        try {
            blogService.getBlog(blogId);
            dbsResponseEntity.setMessage("Blog deleted successfully.");
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("v1/blogs")
    public ResponseEntity<DbsResponseEntity> getBlogsCall(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "id") String sortBy,
                                                          @RequestParam(defaultValue = "1") Long userId){
        Blog blog = new Blog();
        CommonPaginationRequest commonPaginationRequest = new CommonPaginationRequest();
        commonPaginationRequest.setPageNo(pageNo);
        commonPaginationRequest.setPageSize(pageSize);
        commonPaginationRequest.setValue(userId);
        commonPaginationRequest.setSortBy(sortBy);
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
        try {
            List<Blog> gettedBlogs = blogService.getBlogs(commonPaginationRequest);
            dbsResponseEntity.setMessage("Blogs getted successfully.");
            dbsResponseEntity.setData(gettedBlogs);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
