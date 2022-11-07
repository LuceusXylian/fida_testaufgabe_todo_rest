package com.example.todo.todo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping(path = "api/v1/todo")
public class TodoController {
    private ArrayList<Todo> todo_list = new ArrayList<>();

    @GetMapping
    @RequestMapping(path = "list")
    public ArrayList<Todo> getTodos() {
        return todo_list;
    }

    @PostMapping
    @RequestMapping(path = "add")
    public Todo addTodo(String description, String deadlineDate) {
        Todo todo = new Todo(description, deadlineDate);
        todo_list.add(todo);
        return todo;
    }

    @PostMapping
    @RequestMapping(path = "delete")
    public String deleteTodo(long id) {
        for (Todo todo : todo_list) {
            if (todo.getId() == id) {
                todo_list.remove(todo);
                break;
            }
        }
        return "OK";
    }

}
