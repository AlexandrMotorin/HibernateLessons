package org.example.youtubeh1.dao.impl;

import org.example.youtubeh1.dao.StudentDao;
import org.example.youtubeh1.model.persistance.Student;

import java.util.List;
import java.util.Optional;

public class StudentDaoNativeImpl implements StudentDao {
    @Override
    public Optional<Student> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public Optional<Student> update(Long key, Student student) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> deleteById(Long key) {
        return Optional.empty();
    }

    @Override
    public List<Student> findByFirstNamePrefix(String startsName) {
        return null;
    }

    @Override
    public List<Student> findByGroupId(Long id) {
        return null;
    }

    @Override
    public List<Student> findByGroupIdWithSortedByBirthday(Long groupId) {
        return null;
    }
}
