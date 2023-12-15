package vn.edu.iuh.fit.myblog.service;

import vn.edu.iuh.fit.myblog.payload.PostDto;
import vn.edu.iuh.fit.myblog.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto,long id);
    void deletePostById(long id);

}
