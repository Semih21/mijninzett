package com.mijninzet.projectteamdrie.model.comparator;

import com.mijninzet.projectteamdrie.model.entity.Subject;

import java.util.Comparator;

public class SubjectNameComparator implements Comparator<Subject> {


    @Override
    public int compare(Subject sub1, Subject sub2) {
        int primary = sub1.getSubjectName().compareToIgnoreCase(sub2.getSubjectName());
        return primary != 0 ? primary
                :sub2.getExplanation().compareToIgnoreCase(sub2.getExplanation());
    }
}
