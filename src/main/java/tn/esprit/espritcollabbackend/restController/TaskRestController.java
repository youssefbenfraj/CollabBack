package tn.esprit.espritcollabbackend.restController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.espritcollabbackend.entities.Revision;
import tn.esprit.espritcollabbackend.entities.Task;
import tn.esprit.espritcollabbackend.repositories.TaskRepository;
import tn.esprit.espritcollabbackend.services.ITask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")
public class TaskRestController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ITask taskService;
    @PostMapping("/addTask1")
    public ResponseEntity<Task> createNewTask(@RequestBody Map<String, Object> request) {
        // Validate required fields
        Long userId = Optional.ofNullable(request.get("userId"))
                .map(Object::toString)
                .map(Long::parseLong)
                .orElseThrow(() -> new IllegalArgumentException("User ID is required and must be a number."));

       /* String task = Optional.ofNullable(request.get("task"))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalArgumentException("Task is required.")); */
        String task = Optional.ofNullable(request.get("task"))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalArgumentException("Task is required."));

        String description = Optional.ofNullable(request.get("description"))
                .map(Object::toString)
                .orElse(null); // Description is optional

//        String dateString = Optional.ofNullable(request.get("date"))
//                .map(Object::toString)
//                .orElseThrow(() -> new IllegalArgumentException("Date is required and must be in format yyyy-MM-dd."));

        Boolean isFavorite = Optional.ofNullable(request.get("isFavorite"))
                .map(Object::toString)
                .map(Boolean::parseBoolean)
                .orElse(false);

        Boolean completed = Optional.ofNullable(request.get("completed"))
                .map(Object::toString)
                .map(Boolean::parseBoolean)
                .orElse(false);

        // Convert date string to Date object
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date;
//        try {
//            date = dateFormat.parse(dateString);
//        } catch (ParseException e) {
//            throw new RuntimeException("Failed to parse date string: " + dateString, e);
//        }

        // Create and populate the Task entity
        Task newTask = new Task();
        newTask.setTask(task);
        newTask.setDescription(description);
//        newTask.setDate(date);
        newTask.setIsFavorite(isFavorite);
        newTask.setCompleted(completed);

        // Save the task via the service layer
        Task createdTask = taskService.createNewTask(newTask, userId);

        // Return the created Task with HTTP Status code 201
        return ResponseEntity.status(201).body(createdTask);
    }


    /*@PostMapping("/tasks")
    public Task createNewTask(@RequestBody Task task, @RequestParam("userId") Long userId) {
        return taskService.createNewTask(task, userId);
    } /
    @GetMapping("/getTask")
    public ResponseEntity<List<Task>> getAllTasks1() {
        return ResponseEntity.ok(taskService.getAllTask());
    }
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks() {
        return ResponseEntity.ok(taskService.findAllCompletedTask());
    }
    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllInCompleteTask());
    }
   /* @PostMapping("/addTask1")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    } */
    @PutMapping("/updateTask1/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }
    @PutMapping("/toggleFavorite/{id}")
    public ResponseEntity<Task> toggleFavorite(@PathVariable Long id) {
        Task updatedTask = taskService.toggleFavorite(id);
        return ResponseEntity.ok(updatedTask);
    }
    @GetMapping("/tasks/sortedByPriority")
    public ResponseEntity<List<Task>> getTasksSortedByPriority() {
        List<Task> tasks = taskService.getTasksSortedByPriority();
        return ResponseEntity.ok(tasks);
    }
    @DeleteMapping("/deleteT/{id}")
    public void deleteById(@PathVariable Long id){
        taskService.deleteTById(id);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getTasksSortedByPriority();
        return ResponseEntity.ok(tasks);
    }

}
