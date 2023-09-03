package vn.iuh.edu.fit.TodoWithRole.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.iuh.edu.fit.TodoWithRole.dto.TodoDto;
import vn.iuh.edu.fit.TodoWithRole.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    //http://localhost:8080/api/todos
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto createTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(createTodoDto, HttpStatus.CREATED);
    }

    //    http://localhost:8080/api/todos/{id}
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getToDo(@PathVariable Long id) {
        TodoDto todoDto = todoService.getTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoDtoList = todoService.getAllTodos();
        return new ResponseEntity<>(todoDtoList, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id) {
        TodoDto updateTodo = todoService.updateTodo(todoDto, id);
        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Delete success todo id: " + id, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
        TodoDto completeTodoDto = todoService.completeTodo(id);
        return new ResponseEntity<>(completeTodoDto, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id) {
        TodoDto completeTodoDto = todoService.inCompleteTodo(id);
        return new ResponseEntity<>(completeTodoDto, HttpStatus.OK);
    }
}
