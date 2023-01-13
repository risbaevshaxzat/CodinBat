package uz.pdp.task_codingba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.controller.payload.ExampleDto;
import uz.pdp.task_codingba.entite.Example;
import uz.pdp.task_codingba.service.ExampleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    ExampleService exampleService;

    @PostMapping
    public HttpEntity<ApiResponse> addExample( @RequestBody ExampleDto exampleDto) {

        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Example>> getExamples() {

        List<Example> exampleList = exampleService.getExamples();
        return ResponseEntity.ok(exampleList);
    }

    @GetMapping("/{id}")
    public HttpEntity<Example> getExample(@PathVariable Integer id) {

        Example example = exampleService.getExampleById(id);
        return ResponseEntity.ok(example);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editExample(@PathVariable Integer id, @RequestBody ExampleDto exampleDto) {

        ApiResponse apiResponse = exampleService.editExample(id, exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteExample(@PathVariable Integer id) {

        ApiResponse apiResponse = exampleService.deleteExample(id);
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
