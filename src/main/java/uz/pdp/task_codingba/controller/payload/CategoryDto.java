package uz.pdp.task_codingba.controller.payload;

import lombok.Data;

@Data
public class CategoryDto {

    private String name;
    private String description;
    private Integer languageId;
}
