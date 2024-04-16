package org.grhncnrbs.blog.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BlogResponse {

    @Id
    private Long blogId;
    private String title;
    private String description;
    private Boolean publish;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
