package org.example.youtubeh1.dao.impl;

import org.example.youtubeh1.dao.StudentDao;
import org.example.youtubeh1.model.persistance.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.example.youtubeh1.dao.HibernateTest.*;
import static org.junit.Assert.*;

public class WriteStudentDaoImplTest {
    private final StudentDao dao = new StudentDaoJpqlImpl();

    @Test
    public void findUserById() {
        Optional<Student> actual = dao.findById(1L);

        assertTrue(actual.isPresent());
        assertEquals(STUDENT_1, actual.get());
    }

    @Test
    public void findAll() {
        List<Student> expected = Arrays.asList(STUDENT_1, STUDENT_2);

        List<Student> actual = dao.findAll();

        assertFalse(actual.isEmpty());
        assertEquals(actual.size(), expected.size());

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

}
