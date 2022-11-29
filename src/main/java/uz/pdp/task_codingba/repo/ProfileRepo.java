package uz.pdp.task_codingba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_codingba.entite.Profile;

public interface ProfileRepo extends JpaRepository<Profile , Integer> {
}
