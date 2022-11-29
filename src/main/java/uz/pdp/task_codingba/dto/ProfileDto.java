package uz.pdp.task_codingba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ProfileDto {
    private Integer id;
    private String name;
    private String surname;
    private LocalDateTime ans_date;
    private LocalDateTime create_data;
    private LocalDateTime update_date;
}
