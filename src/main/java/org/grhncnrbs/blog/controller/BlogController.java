package org.grhncnrbs.blog.controller;

import jakarta.validation.Valid;
import org.grhncnrbs.blog.dto.DbsResponseEntity;
import org.grhncnrbs.blog.dto.UpdateBlogRequest;
import org.grhncnrbs.blog.model.Blog;
import org.grhncnrbs.blog.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PutMapping("v1/blogs")
    public ResponseEntity<> updateBlogCall(@Valid @RequestBody UpdateBlogRequest updateBlogRequest){
        Blog blog = new Blog();
        DbsResponseEntity dbsResponseEntity = new DbsResponseEntity();
        try {
            BeanUtils.copyProperties(updateBlogRequest, blog);
            Blog updatedBlog = blogService.updateBlog(blog);
            dbsResponseEntity.setMessage("Blog updated successfully.");
            dbsResponseEntity.setData(updatedBlog);
            return ResponseEntity.ok(dbsResponseEntity);
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
