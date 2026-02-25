package hexlet.code.service.impl;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.UserMapper;
import hexlet.code.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    UserDTO create(UserCreateDTO data) {
        var user = userMapper.map(data);
        repository.save(user);
        var userDTO = userMapper.map(user);
        return userDTO;
    }

    void delete(Long id) {
        repository.deleteById(id);
    }

    UserDTO findById(Long id) {
        var user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var userDTO = userMapper.map(user);
        return userDTO;
    }

    public List<UserDTO> getAll() {
        var users = repository.findAll();
        var result = users.stream().map(userMapper::map).toList();
        return result;
    }

    UserDTO update(UserUpdateDTO data, Long id) {
        var user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        userMapper.update(data, user);
        repository.save(user);
        var userDTO = userMapper.map(user);
        return userDTO;
    }
}
