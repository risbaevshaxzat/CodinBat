package uz.pdp.task_codingba.repo;

import com.example.lesson_1_task_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
}
