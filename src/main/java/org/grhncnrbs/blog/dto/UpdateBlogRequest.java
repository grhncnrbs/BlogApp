package org.grhncnrbs.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBlogRequest {

    @NotNull(message = "blogId is required parameter")
    private Long blogId;

    @NotBlank(message = "title is required parameter")
    private String title;

    @NotBlank(message = "description is required parameter")
    private String description;

    @NotNull(message = "publish is required parameter")
    private Boolean publish;

    @NotNull(message = "userId is required parameter")
    private Long userId;

}
