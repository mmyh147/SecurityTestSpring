package com.example.securitytestspring.Repository;

import com.example.securitytestspring.Model.Todo;
import com.example.securitytestspring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {


    Todo findTodoById(Integer id);

    List<Todo> findAllByUser(User user);
}
