package vn.edu.iuh.fit.myblog.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.myblog.entity.Category;
import vn.edu.iuh.fit.myblog.entity.Post;
import vn.edu.iuh.fit.myblog.exception.ResourceNotFoundException;
import vn.edu.iuh.fit.myblog.payload.PostDto;
import vn.edu.iuh.fit.myblog.payload.PostResponse;
import vn.edu.iuh.fit.myblog.repository.CategoryRepository;
import vn.edu.iuh.fit.myblog.repository.PostRepository;
import vn.edu.iuh.fit.myblog.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;


    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
        Post post = mapper(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        return mapper(newPost);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        // custom sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        // get Page post
        Page<Post> postPage = postRepository.findAll(PageRequest.of(pageNo, pageSize, sort));
        List<Post> postList = postPage.getContent();
        // convert to POstResponse
        List<PostDto> postDtoList = postList.stream().map(this::mapper).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNo(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setLast(postPage.isLast());
        return postResponse;
    }


    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapper(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        Post saved = postRepository.save(post);
        return mapper(saved);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> findPostByCategory(Long categoryId) {
        return postRepository.findByCategoryId(categoryId).stream().map((element) -> modelMapper.map(element, PostDto.class)).collect(Collectors.toList());
    }

    private Post mapper(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    private PostDto mapper(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
