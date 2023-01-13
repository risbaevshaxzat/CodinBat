package uz.pdp.task_codingba.repo;

import com.example.lesson_1_task_2.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
