package vn.edu.iuh.fit.myblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private  String email;
    private  String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(Long id, String body, String email, String name) {
        this.id = id;
        this.body = body;
        this.email = email;
        this.name = name;
    }

    public Comment(String body, String email, String name) {
        this.body = body;
        this.email = email;
        this.name = name;
    }
}
