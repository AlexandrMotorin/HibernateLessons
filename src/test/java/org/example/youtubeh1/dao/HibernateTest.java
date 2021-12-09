package org.example.youtubeh1.dao;

import org.example.youtubeh1.dao.impl.GroupDaoImpl;
import org.example.youtubeh1.dao.impl.StudentDaoImpl;
import org.example.youtubeh1.dao.impl.ReadStudentDaoImplTest;
import org.example.youtubeh1.dao.impl.WriteStudentDaoImplTest;
import org.example.youtubeh1.model.persistance.Address;
import org.example.youtubeh1.model.persistance.Group;
import org.example.youtubeh1.model.persistance.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.time.LocalDate;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ReadStudentDaoImplTest.class,
        WriteStudentDaoImplTest.class
})
public class HibernateTest {

    private final static StudentDao STUDENT_DAO = new StudentDaoImpl();
    private final static GroupDao GROUP_DAO = new GroupDaoImpl();

    public static final Address ADDRESS_1 = new Address();
    public static final Address ADDRESS_2 = new Address();
    public static final Student STUDENT_1 = new Student();
    public static final Student STUDENT_2 = new Student();
    public static final Group GROUP = new Group();

    @BeforeClass
    public static void onStart(){
        deleteData();
        loadData();
    }

    @AfterClass
    public static void onEnd(){
        deleteData();
    }

    public static void loadData(){
        GROUP.setName("Test group name");
        GROUP_DAO.save(GROUP);

        ADDRESS_1.setAddress("Test address");
        ADDRESS_2.setAddress("Other test address");

        STUDENT_1.setAddress(ADDRESS_1);
        STUDENT_1.setFirstName("Ivan");
        STUDENT_1.setLastName("Ivanov");
        STUDENT_1.setBirthday(LocalDate.of(1999,1,1));
        STUDENT_1.setGroup(GROUP);

        STUDENT_2.setAddress(ADDRESS_2);
        STUDENT_2.setFirstName("Alex");
        STUDENT_2.setLastName("Wilders");
        STUDENT_2.setBirthday(LocalDate.of(1980, 3, 4));
        STUDENT_2.setGroup(GROUP);

        STUDENT_DAO.save(STUDENT_1);
        STUDENT_DAO.save(STUDENT_2);
    }


    public static void deleteData(){
        STUDENT_DAO.findAll().forEach(s -> STUDENT_DAO.deleteById(s.getId()));
        GROUP_DAO.findAll().forEach(group -> GROUP_DAO.deleteById(group.getId()));
    }

}
