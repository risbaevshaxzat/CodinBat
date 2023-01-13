package uz.pdp.task_codingba.controller.payload;

import lombok.Data;

@Data
public class TaskDto {

    private String name;
    private String text;
    private String solution;
    private String hint;
    private String method;
    private Integer userId;
    private Integer languageId;
}
