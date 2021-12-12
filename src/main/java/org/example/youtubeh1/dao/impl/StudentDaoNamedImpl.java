package org.example.youtubeh1.dao.impl;

import org.example.youtubeh1.dao.StudentDao;
import org.example.youtubeh1.model.persistance.Student;
import org.example.youtubeh1.util.SessionUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class StudentDaoNamedImpl implements StudentDao {

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
        try(Session session = SessionUtil.createSession()){
            return session.createNamedQuery(Student.PREFIX_QUERY_NAME, Student.class)
                    .setParameter(1, startsName + "%")
                    .getResultList();

        }
    }

    @Override
    public List<Student> findByGroupId(Long id) {
        try(Session session = SessionUtil.createSession()) {
            return session.createNamedQuery(Student.BY_GROUP_ID_QUERY_NAME, Student.class)
                    .setParameter(1, id)
                    .getResultList();
        }
    }

    @Override
    public List<Student> findByGroupIdWithSortedByBirthday(Long id) {
        try(Session session = SessionUtil.createSession()) {
            return session.createNamedQuery(Student.BY_GROUP_ID_AND_SORTED_QUERY_NAME, Student.class)
                    .setParameter("groupId", id)
                    .getResultList();
        }
    }
}
