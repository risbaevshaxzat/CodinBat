package uz.pdp.task_codingba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_codingba.controller.payload.AnswerDto;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.entite.Answer;
import uz.pdp.task_codingba.entite.Task;
import uz.pdp.task_codingba.entite.User;
import uz.pdp.task_codingba.repo.AnswerRepository;
import uz.pdp.task_codingba.repo.TaskRepository;
import uz.pdp.task_codingba.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse addAnswer(AnswerDto answerDto) {

        Answer answer = new Answer();
        answer.setText(answerDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        answer.setTask(optionalTask.get());

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer added", true);
    }

    public List<Answer> getAnswers() {

        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }

    public Answer getAnswerById(Integer id) {

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    public ApiResponse editAnswer(Integer id, AnswerDto answerDto) {

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()) {
            return new ApiResponse("Answer not found", false);
        }
        Answer editingAnswer = new Answer();
        editingAnswer.setText(answerDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (optionalTask.isEmpty()) {
            return new ApiResponse("Task not found", false);
        }
        editingAnswer.setTask(optionalTask.get());

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        editingAnswer.setUser(optionalUser.get());
        answerRepository.save(editingAnswer);
        return new ApiResponse("Answer edited", true);

    }

    public ApiResponse deleteAnswer(Integer id) {

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isEmpty()) {
            return new ApiResponse("Answer not found", false);
        }
        answerRepository.deleteById(id);
        return new ApiResponse("Answer deleted", true);
    }
}
