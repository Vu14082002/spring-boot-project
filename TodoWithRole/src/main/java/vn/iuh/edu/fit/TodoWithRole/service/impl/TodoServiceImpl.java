package vn.iuh.edu.fit.TodoWithRole.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.iuh.edu.fit.TodoWithRole.Mapper.AutoTodoMapper;
import vn.iuh.edu.fit.TodoWithRole.dto.TodoDto;
import vn.iuh.edu.fit.TodoWithRole.entities.Todo;
import vn.iuh.edu.fit.TodoWithRole.exception.ResultNotFoundException;
import vn.iuh.edu.fit.TodoWithRole.repository.TodoRepository;
import vn.iuh.edu.fit.TodoWithRole.service.TodoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = AutoTodoMapper.MAPPER.mapToTodo(todoDto);
        Todo savedTodo = todoRepository.save(todo);
        return AutoTodoMapper.MAPPER.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(() -> new ResultNotFoundException("Todo not found id: " + id));
        return AutoTodoMapper.MAPPER.mapToTodoDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().map(e->AutoTodoMapper.MAPPER.mapToTodoDto(e)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Optional<Todo> findTodoById = todoRepository.findById(id);
        if(findTodoById.isEmpty()){
           throw  new ResultNotFoundException("Todo not found id: "+ id);
        }
        todoDto.setId(id);
        Todo savedTodo = todoRepository.save(AutoTodoMapper.MAPPER.mapToTodo(todoDto));
        return AutoTodoMapper.MAPPER.mapToTodoDto(savedTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        Optional<Todo> findTodoById = todoRepository.findById(id);
        if(findTodoById.isEmpty()){
            throw  new ResultNotFoundException("Todo not found id: "+ id);
        }
        todoRepository.delete(findTodoById.get());
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Optional<Todo> findTodoById = todoRepository.findById(id);
        if(findTodoById.isEmpty()){
            throw  new ResultNotFoundException("Todo not found id: "+ id);
        }
        findTodoById.get().setComplete(Boolean.TRUE);
        Todo savedTodo = todoRepository.save(findTodoById.get());
        return AutoTodoMapper.MAPPER.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Optional<Todo> findTodoById = todoRepository.findById(id);
        if(findTodoById.isEmpty()){
            throw  new ResultNotFoundException("Todo not found id: "+ id);
        }
        findTodoById.get().setComplete(Boolean.FALSE);
        Todo savedTodo = todoRepository.save(findTodoById.get());
        return AutoTodoMapper.MAPPER.mapToTodoDto(savedTodo);
    }
}
