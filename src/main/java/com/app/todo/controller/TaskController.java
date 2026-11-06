package com.app.todo.controller;

//import ch.qos.logback.core.model.Model;
import org.jspecify.annotations.Nullable;
import org.springframework.ui.Model;
import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import com.app.todo.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    public final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTask(Model model) {

        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toogleTask(@PathVariable Long id) {
        taskService.toogleTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task",task);
        return "editTask";
    }

    @PostMapping("/{id}/edit")
    public String updateTask(@PathVariable Long id, @RequestParam String title) {
        taskService.updateTask(id, title);
        return "redirect:/tasks";
    }
}
