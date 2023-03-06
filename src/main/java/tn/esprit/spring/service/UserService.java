package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IuserService;

@Service

public class UserService implements IuserService {
    @Autowired
    private UserRepository userRepository;

    public void UserRepository(int userId) {
        User user = userRepository.findById(userId).get();
        // rest of your code here
    }
}
