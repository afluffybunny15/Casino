package usermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usermanager.entity.User;
import usermanager.repositories.UserRepository;
import usermanager.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean userExists ( final String username ) {
        return userRepository.existsByUsername( username );
    }

    @Override
    public User getCurrentUser () {
        // TODO Auto-generated method stub
        return null;
    }

}
