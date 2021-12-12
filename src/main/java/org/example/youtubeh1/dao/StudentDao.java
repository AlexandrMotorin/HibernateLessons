package org.example.youtubeh1.dao;

import org.example.youtubeh1.model.persistance.Student;

import java.util.List;

public interface StudentDao extends GenericDao<Student,Long>{
    List<Student> findByFirstNamePrefix(String startsName);
    List<Student> findByGroupId(Long id);
    List<Student> findByGroupIdWithSortedByBirthday(Long groupId);
}
