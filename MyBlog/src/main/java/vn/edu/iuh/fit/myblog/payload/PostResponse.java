package vn.edu.iuh.fit.myblog.payload;

import lombok.*;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalPage;
    private  boolean last;
}
