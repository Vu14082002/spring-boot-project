package vn.edu.iuh.fit.myblog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name should be null or empty")
    private String name;

    @Email
    @NotEmpty(message = "Email should be null or empty")
    private String email;
    @NotEmpty
    @Size(min = 2, message = "Comment body must be minimum 2 characters ")
    private String body;


    public CommentDto(String body, String email, String name) {
        this.body = body;
        this.email = email;
        this.name = name;
    }
}
