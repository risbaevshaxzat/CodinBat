package uz.pdp.task_codingba.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.controller.payload.ExampleDto;
import uz.pdp.task_codingba.entite.Example;
import uz.pdp.task_codingba.entite.Task;
import uz.pdp.task_codingba.repo.ExampleRepository;
import uz.pdp.task_codingba.repo.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    TaskRepository taskRepository;

    public ApiResponse addExample(ExampleDto exampleDto) {

        Example example = new Example();
        example.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Example added", true);
    }

    public List<Example> getExamples() {

        List<Example> exampleList = exampleRepository.findAll();
        return exampleList;
    }

    public Example getExampleById(Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    public ApiResponse editExample(Integer id, ExampleDto exampleDto) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty()) {
            return new ApiResponse("Example not found", false);
        }

        Example editingExample = new Example();
        editingExample.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        editingExample.setTask(optionalTask.get());
        exampleRepository.save(editingExample);
        return new ApiResponse("Example edited", true);

    }

    public ApiResponse deleteExample(Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (optionalExample.isEmpty()) {
            return new ApiResponse("Example not found", false);
        }
        exampleRepository.deleteById(id);
        return new ApiResponse("Example deleted", true);
    }
}
