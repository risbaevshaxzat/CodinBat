package uz.pdp.task_codingba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_codingba.entite.Category;

public interface CategoryRepo extends JpaRepository<Category , Integer> {
}
