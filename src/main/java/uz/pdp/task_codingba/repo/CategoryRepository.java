package uz.pdp.task_codingba.repo;

import com.example.lesson_1_task_2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
