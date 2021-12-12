package org.example.youtubeh1.dao.impl;

import org.example.youtubeh1.dao.StudentDao;
import org.example.youtubeh1.model.persistance.Student;
import org.example.youtubeh1.util.SessionUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class StudentDaoCriteriaImpl implements StudentDao {
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
    public List<Student> findByFirstNamePrefix(String prefix) {
        try(Session session = SessionUtil.createSession()){

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> query = builder.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);

            query.select(root)
                    .where(
                            builder.like(root.get("firstName"),prefix+"%")
                    );

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Student> findByGroupId(Long id) {
        try (Session session = SessionUtil.createSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> query = builder.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);

            query.select(root).where(
                builder.equal(root.get("group").get("id"),id)
            );

            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public List<Student> findByGroupIdWithSortedByBirthday(Long id) {
        try (Session session = SessionUtil.createSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> query = builder.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);

            query.select(root).where(
                    builder.equal(root.get("group").get("id"),id)
            ).orderBy(builder.asc(root.get("birthday")));

            return session.createQuery(query).getResultList();
        }
    }
}
