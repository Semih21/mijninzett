package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Exception;

import java.util.List;

public interface ExceptionService {
    List<Exception> findAll();
    Exception findById(int theId);
    void save(Exception theException);
    void deleteById(int theId);
    List<Exception>findByUserId();

}
