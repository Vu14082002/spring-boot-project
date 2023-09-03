package vn.iuh.edu.fit.TodoWithRole.service;

import vn.iuh.edu.fit.TodoWithRole.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodo(TodoDto todoDto, Long id);
    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);
    TodoDto inCompleteTodo(Long id);


}
