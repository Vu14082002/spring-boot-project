package vn.iuh.edu.fit.TodoWithRole.Mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.iuh.edu.fit.TodoWithRole.dto.TodoDto;
import vn.iuh.edu.fit.TodoWithRole.entities.Todo;

@Mapper
public interface AutoTodoMapper {
    AutoTodoMapper MAPPER = Mappers.getMapper(AutoTodoMapper.class); // bien dich thi chay
    TodoDto mapToTodoDto(Todo todo);
    Todo mapToTodo(TodoDto todoDto);

}
