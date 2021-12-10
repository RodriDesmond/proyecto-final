package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.entity.User;
import com.informatorio.proyectofinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAuthorizationService {
    @Autowired
    UserRepository userRepository;

    public boolean canUpdate(long loggedInUser, long userId){
        if(loggedInUser != userId)
            return false;
        Optional<User> optional = userRepository.findById(userId);
        return optional.isPresent();

        /*User inDB = optional.get();
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        if(inDB.getLastUpdated() != null && inDB.getLastUpdated().isAfter(twentyFourHoursAgo))
            return false;*/
    }
}
