package vn.iuh.edu.fit.TodoWithRole.Mapper;

import vn.iuh.edu.fit.TodoWithRole.dto.TodoDto;
import vn.iuh.edu.fit.TodoWithRole.entities.Todo;

public class TodoMapper {

    public static TodoDto mapToTodo(Todo todo){
       return new TodoDto(todo.getId(),todo.getTitle(),todo.getDescription(), todo.isComplete());
    }
    public static Todo mapToToDo(TodoDto todoDto){
        return new Todo(todoDto.getId(),todoDto.getTitle(),todoDto.getDescription(), todoDto.isComplete());
    }
}
