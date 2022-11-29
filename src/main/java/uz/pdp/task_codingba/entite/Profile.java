package uz.pdp.task_codingba.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.NotFound;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@Entity
public class Profile {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)

    @ManyToOne
    @JoinColumn(nullable = false)
    Example example_id;

    private LocalDateTime ans_date;
    @Column(nullable = false)
    private LocalDateTime create_data;
    @Column(nullable = false)
    private LocalDateTime update_date;
}
