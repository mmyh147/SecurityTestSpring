package com.example.securitytestspring.Controller;

import com.example.securitytestspring.Model.Todo;
import com.example.securitytestspring.Model.User;
import com.example.securitytestspring.Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get-all")
    public ResponseEntity getAllTodos(){
        return ResponseEntity.status(200).body(todoService.getAll());
    }

    @GetMapping("/get")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(todoService.getMyToDo(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal User user, @RequestBody @Valid Todo todo){
        todoService.addTodo(user.getId(), todo);
        return ResponseEntity.ok("todo added");
    }



    @DeleteMapping("/delete/{todo_id}")
    public ResponseEntity delete(@AuthenticationPrincipal User user, @PathVariable Integer todo_id){
        todoService.delete(todo_id, user.getId());
        return ResponseEntity.ok("todo deleted");
    }


    @PutMapping("/update/{todo_id}")
    public ResponseEntity update(@AuthenticationPrincipal User user, @PathVariable Integer todo_id, @RequestBody @Valid Todo todo){
        todoService.update(user.getId(),todo_id, todo);
        return ResponseEntity.ok("todo updated");
    }
}
