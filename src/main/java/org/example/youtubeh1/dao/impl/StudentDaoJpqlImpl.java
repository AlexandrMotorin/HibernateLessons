package org.example.youtubeh1.dao.impl;

import lombok.RequiredArgsConstructor;
import org.example.youtubeh1.dao.StudentDao;
import org.example.youtubeh1.model.persistance.Student;
import org.example.youtubeh1.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StudentDaoJpqlImpl implements StudentDao {
    @Override
    public Optional<Student> findById(Long id) {
        Session session = SessionUtil.createSession();
        Transaction transaction = session.beginTransaction();

        Optional<Student> student = session.createQuery("from Student u where id=:studentId", Student.class)
                .setParameter("studentId", id)
                .uniqueResultOptional();


        transaction.commit();
        session.close();

        return student;
    }

    @Override
    public List<Student> findAll() {
        Session session = SessionUtil.createSession();

        List<Student> students = session.createQuery("from Student", Student.class).getResultList();

        session.close();

        return students;
    }

    @Override
    public Student save(Student student) {
        Session session = SessionUtil.createSession();
        Transaction transaction = session.beginTransaction();

        session.save(student.getAddress());
        session.save(student);

        transaction.commit();
        session.close();

        return student;
    }

    @Override
    public Optional<Student> update(Long id, Student student) {
        Optional<Student> byId = findById(id);

        if(byId.isEmpty()){
            System.out.println("(UPDATE)Student with id " + id + " does note exist");
            return byId;
        }

        Session session = SessionUtil.createSession();
        Transaction transaction = session.beginTransaction();

        student.setId(id);
        session.update(student);

        transaction.commit();
        session.close();
        return Optional.of(student);
    }

    @Override
    public Optional<Student> deleteById(Long id) {

        Optional<Student> student = findById(id);

        if(student.isEmpty()){
            System.out.println("(UPDATE)Student with id " + id + " does note exist");
            return student;
        }

        Session session = SessionUtil.createSession();
        Transaction transaction = session.beginTransaction();

        session.delete(student.get());

        transaction.commit();
        session.close();
        return student;
    }


    @Override
    public List<Student> findByFirstNamePrefix(String startsName) {

        Session session = SessionUtil.createSession();

        List<Student> resultList = session.createQuery("from Student where firstName like ?1", Student.class)
                .setParameter(1, startsName + "%")
                .getResultList();

        session.close();

        return resultList;
    }

    @Override
    public List<Student> findByGroupId(Long id) {
        try(Session session = SessionUtil.createSession()) {
            return session.createQuery("from Student where group.id  = ?1", Student.class)
                    .setParameter(1, id)
                    .getResultList();
        }
    }

    @Override
    public List<Student> findByGroupIdWithSortedByBirthday(Long id) {
        try(Session session = SessionUtil.createSession()) {
            return session.createQuery("from Student s where s.group.id  = ?1 " +
                    "order by s.birthday", Student.class)
                    .setParameter(1, id)
                    .getResultList();
        }
    }
}
