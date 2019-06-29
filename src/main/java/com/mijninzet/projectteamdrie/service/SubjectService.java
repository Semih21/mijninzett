package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject>findAll(){
        return new ArrayList<>(subjectRepository.findAll());
    }

    public void addSubject(Subject subject){
        subjectRepository.save(subject);
    }

    public void deleteSubjectById(int id){
        subjectRepository.deleteById(id);
    }

    public Subject findById(int id){
        return subjectRepository.findSubjectBySubjectId(id);
    }
}
