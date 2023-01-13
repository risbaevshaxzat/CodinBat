package uz.pdp.task_codingba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_codingba.controller.payload.ApiResponse;
import uz.pdp.task_codingba.controller.payload.UserDto;
import uz.pdp.task_codingba.entite.User;
import uz.pdp.task_codingba.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse createUser(UserDto userDto) {

        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if (existsByEmail) {
            return new ApiResponse("Such a email already exist with another User", false);
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User created", true);
    }

    public List<User> getUsers() {

        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse editUser(Integer id, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("user not found", false);
        }
        User editingUser = optionalUser.get();
        editingUser.setEmail(userDto.getEmail());
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if (existsByEmail) {
            return new ApiResponse("Such a email already exist with another User", false);
        }
        editingUser.setPassword(userDto.getPassword());
        userRepository.save(editingUser);
        return new ApiResponse("User edited", true);
    }

    public ApiResponse deleteUser(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        userRepository.deleteById(id);
        return new ApiResponse("User deleted", true);
    }
}
