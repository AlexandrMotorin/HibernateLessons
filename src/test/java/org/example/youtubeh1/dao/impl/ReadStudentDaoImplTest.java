package org.example.youtubeh1.dao.impl;

import lombok.RequiredArgsConstructor;
import org.example.youtubeh1.dao.StudentDao;
import org.example.youtubeh1.model.persistance.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.example.youtubeh1.dao.HibernateTest.*;
import static org.junit.Assert.*;


public class ReadStudentDaoImplTest {

    private final StudentDao dao = new StudentDaoJpqlImpl();

    @Test
    public void findByIdTest(){
        Optional<Student> actual = dao.findById(1L);

        assertTrue(actual.isPresent());
        assertEquals(STUDENT_1, actual.get());
    }

    @Test
    public void findAllTest(){
        List<Student> actual = dao.findAll();
        List<Student> expected = Arrays.asList(STUDENT_1,STUDENT_2);

        assertFalse(actual.isEmpty());

        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i),actual.get(i));
        }
    }

    @Test
    public void findAll() {
        List<Student> expected = Arrays.asList(STUDENT_1, STUDENT_2);

        List<Student> actual = dao.findAll();

        assertTrue(!actual.isEmpty());
        assertTrue(actual.size() == expected.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void findByStartsFirstName() {
        String startsName = "Al";

        List<Student> students = dao.findByFirstNamePrefix(startsName);

        assertFalse(students.isEmpty());

        for (Student student : students) {
            assertTrue(student.getFirstName().startsWith(startsName));
        }
    }

    @Test
    public void findByGroupId(){
        Long groupId = 1L;

        List<Student> actual = dao.findByGroupId(groupId);

        assertFalse(actual.isEmpty());

        for(Student student: actual){
            assertEquals(groupId,student.getGroup().getId());
        }
    }

}