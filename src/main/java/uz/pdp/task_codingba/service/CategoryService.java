package uz.pdp.task_codingba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.controller.payload.CategoryDto;
import uz.pdp.task_codingba.entite.Category;
import uz.pdp.task_codingba.entite.Language;
import uz.pdp.task_codingba.repo.CategoryRepository;
import uz.pdp.task_codingba.repo.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addCategory(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }
        category.setLanguage(optionalLanguage.get());
        categoryRepository.save(category);
        return new ApiResponse("Category added", true);
    }

    public List<Category> getCategories() {

        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Category getCategoryById(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }
        Category editingCategory = optionalCategory.get();
        editingCategory.setName(categoryDto.getName());
        editingCategory.setDescription(categoryDto.getDescription());

        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (optionalLanguage.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }
        editingCategory.setLanguage(optionalLanguage.get());
        categoryRepository.save(editingCategory);
        return new ApiResponse("Category edited", true);
    }

    public ApiResponse deleteCategory(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }
        categoryRepository.deleteById(id);
        return new ApiResponse("Category deleted", true);
    }
}
