package org.example.youtubeh1.dao.impl;

import org.example.youtubeh1.dao.GroupDao;
import org.example.youtubeh1.model.persistance.Group;
import org.hibernate.LazyInitializationException;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.Assert.*;

public class ReadGroupDaoImpTest {

    private final GroupDao dao = new GroupDaoJpqlImpl();

    @Test
    public void countStudentsInGroup(){

        OptionalInt actual = dao.countStudentsInGroup(1L);

        assertTrue(actual.isPresent());

        assertEquals(2,actual.getAsInt());
    }

    @Test
    public void findWhereCountStudentsEqualsThenN() {
        int exceptedCount = 2;
        List<Group> actual = dao.findWhereCountStudentsEqualsThenN(exceptedCount);

        assertFalse(actual.isEmpty());

        for(Group group: actual){
            OptionalInt studentsCount = dao.countStudentsInGroup(group.getId());

            assertTrue(studentsCount.isPresent());

            int actualCount = studentsCount.getAsInt();
            assertEquals(exceptedCount,actualCount);
        }
    }

    @Test
    public void findByGroupIdWithStudents() {
        Optional<Group> actual = dao.findByGroupIdWithStudents(1L);

        assertTrue(actual.isPresent());

        Group group = actual.get();

        assertFalse(group.getStudents().isEmpty());
    }

    @Test(expected = LazyInitializationException.class)
    public void catchLazyInitializationExceptionFromFindId() {
        Optional<Group> actual = dao.findById(1L);

        assertTrue(actual.isPresent());

        Group group = actual.get();

        assertNull(group.getStudents());
    }

}
