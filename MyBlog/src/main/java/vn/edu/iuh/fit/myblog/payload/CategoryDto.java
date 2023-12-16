package vn.edu.iuh.fit.myblog.payload;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link vn.edu.iuh.fit.myblog.entity.Category}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto implements Serializable {
    Long id;
    String name;
    String description;
}