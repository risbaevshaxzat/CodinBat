package uz.pdp.task_codingba.controller.payload;

import lombok.Data;

@Data
public class AnswerDto {

    private String text;
    private Integer taskId;
    private Integer userId;

}
