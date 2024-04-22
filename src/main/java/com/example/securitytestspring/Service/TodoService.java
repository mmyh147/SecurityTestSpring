package com.example.securitytestspring.Service;


import com.example.securitytestspring.Api.ApiException;
import com.example.securitytestspring.Model.Todo;
import com.example.securitytestspring.Model.User;
import com.example.securitytestspring.Repository.AuthRepository;
import com.example.securitytestspring.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;

    public List<Todo> getAll(){
        return todoRepository.findAll();
    }

    public List<Todo> getMyToDo(Integer userId){
        User user = authRepository.findUserById(userId);

        return todoRepository.findAllByUser(user);
    }


    public void addTodo(Integer userId, Todo todo){
        User user = authRepository.findUserById(userId);
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void delete(Integer todoId, Integer userId){

        Todo todo = todoRepository.findTodoById(todoId);
        if (todo == null){
            throw new ApiException("todo not found");
        }

        if (todo.getUser().getId() != userId){
            throw new ApiException("User not authorize to delete this task");
        }

        todoRepository.delete(todo);


    }


    public void update(Integer userId, Integer todoId, Todo updatedTodo){
        Todo todo = todoRepository.findTodoById(todoId);
        if (todo == null){
            throw new ApiException("todo not found");
        }

        if (todo.getUser().getId() != userId){
            throw new ApiException("User not authorize to delete this task");
        }

        updatedTodo.setId(todo.getId());
        updatedTodo.setUser(todo.getUser());
        todoRepository.save(updatedTodo);

    }
}
