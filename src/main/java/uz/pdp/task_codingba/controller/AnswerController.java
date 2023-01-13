package uz.pdp.task_codingba.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_codingba.controller.payload.AnswerDto;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.entite.Answer;
import uz.pdp.task_codingba.service.AnswerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping
    public HttpEntity<ApiResponse> addAnswer( @RequestBody AnswerDto answerDto) {

        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers() {

        List<Answer> answerList = answerService.getAnswers();
        return ResponseEntity.ok(answerList);
    }

    @GetMapping("/{id}")
    public HttpEntity<Answer> getAnswer(@PathVariable Integer id) {

        Answer answer = answerService.getAnswerById(id);
        return ResponseEntity.ok(answer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAnswer(@PathVariable Integer id, @RequestBody AnswerDto answerDto) {

        ApiResponse apiResponse = answerService.editAnswer(id, answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteAnswer(@PathVariable Integer id) {

        ApiResponse apiResponse = answerService.deleteAnswer(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
