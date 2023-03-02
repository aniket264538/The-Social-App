package com.socialapp.payloads;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {

    private long id;

//    private PostDto post;

    private Date date;

    @NotEmpty
    @Length(max = 250)
    private String commentContent;

    private UserDto commenter;
}
