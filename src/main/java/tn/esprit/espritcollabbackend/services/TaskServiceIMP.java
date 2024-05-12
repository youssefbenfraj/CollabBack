package tn.esprit.espritcollabbackend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import tn.esprit.espritcollabbackend.entities.Revision;
import tn.esprit.espritcollabbackend.entities.Task;
import tn.esprit.espritcollabbackend.entities.User;
import tn.esprit.espritcollabbackend.repositories.TaskRepository;
import tn.esprit.espritcollabbackend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceIMP implements  ITask{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired

    private UserRepository userRepository;
    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }
    @Override
    public Task createNewTask(Task r, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            r.setUser(user);
            return taskRepository.save(r);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }
   /* public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }*/

   /* @Override
    public Task createNewTask(Task task, Long userId) {
        // Retrieve user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Set the user for the task
        task.setUser(user);

        // Save the task
        return taskRepository.save(task);
    } */
   /* public Task createNewTask(Task task, Long userId) {  // Vérifiez si l'utilisateur avec l'ID spécifié existe
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

    // Associez la tâche à l'utilisateur
        task.setUser(user);

    // Enregistrez la tâche
        return taskRepository.save(task);
} */

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.getById(id);
    }

    public List<Task> findAllCompletedTask() {
        return taskRepository.findByCompletedTrue();
    }

    public List<Task> findAllInCompleteTask() {
        return taskRepository.findByCompletedFalse();
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
    //@Override
    //public void deleteById(Long id) {
    // taskRepository.deleteById(id);
    //}
    @Override
    public void deleteTById(Long id) {
        taskRepository.deleteById(id);

    }
    public Task toggleFavorite(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setIsFavorite(!task.getIsFavorite());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksSortedByPriority() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "priority"));
    }

}
