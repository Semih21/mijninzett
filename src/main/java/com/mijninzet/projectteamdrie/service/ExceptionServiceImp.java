package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.Exception;
import com.mijninzet.projectteamdrie.repository.ExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExceptionServiceImp implements ExceptionService {
    @Autowired
    private ExceptionRepository exceptionRepository;

    @Override
    public List<Exception> findAll() {
        ArrayList<Exception> exceptions = new ArrayList<>();
        exceptionRepository.findAll()
                .forEach(exceptions::add);
        return exceptions;
    }

    @Override
    public List<Exception> findByUserId() {
        ArrayList<Exception> exceptions = new ArrayList<>();
        int userId = UserSingleton.getInstance().getId();
        exceptionRepository.findByUser_Id(userId)
                .forEach(exceptions::add);
        return exceptions;
    }

    @Override
    public Exception findById(int theId) {
        Optional<Exception> result = exceptionRepository.findById(theId);
        Exception theException = null;
        if (result.isPresent()) {
            theException = result.get();
        } else {
            throw new RuntimeException("de incident met " + theId + " kan niet gevonden worden");
        }
        return theException;
    }

    @Override
    public void save(Exception e) {
        exceptionRepository.save(e);

    }

    @Override
    public void deleteById(int theId) {
        exceptionRepository.deleteById(theId);
    }
}
