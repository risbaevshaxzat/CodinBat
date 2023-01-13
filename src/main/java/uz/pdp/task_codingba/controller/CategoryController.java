package uz.pdp.task_codingba.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.controller.payload.CategoryDto;
import uz.pdp.task_codingba.entite.Category;
import uz.pdp.task_codingba.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<ApiResponse> addCategory( @RequestBody CategoryDto categoryDto) {

        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {

        List<Category> categoryList = categoryService.getCategories();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public HttpEntity<Category> getCategory(@PathVariable Integer id) {

        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {

        ApiResponse apiResponse = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {

        ApiResponse apiResponse = categoryService.deleteCategory(id);
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
