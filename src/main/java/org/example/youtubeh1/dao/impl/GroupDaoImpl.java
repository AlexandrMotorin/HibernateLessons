package org.example.youtubeh1.dao.impl;

import org.example.youtubeh1.dao.GroupDao;
import org.example.youtubeh1.model.persistance.Group;
import org.example.youtubeh1.model.persistance.Student;
import org.example.youtubeh1.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class GroupDaoImpl implements GroupDao {
    @Override
    public Optional<Group> findById(Long id) {
        Session session = SessionUtil.createSession();
        Transaction transaction = session.beginTransaction();

        Optional<Group> groupOptional = session.createQuery("from Group u where id=:groupId", Group.class)
                .setParameter("groupId", id)
                .uniqueResultOptional();


        transaction.commit();
        session.close();

        return groupOptional;
    }

    @Override
    public List<Group> findAll() {
        Session session = SessionUtil.createSession();

        List<Group> groups = session.createQuery("from Group", Group.class).getResultList();

        session.close();

        return groups;
    }

    @Override
    public Group save(Group group) {
        Session session = SessionUtil.createSession();
        Transaction transaction = session.beginTransaction();

        session.save(group);

        transaction.commit();
        session.close();

        return group;
    }

    @Override
    public Optional<Group> update(Long key, Group group) {
        return Optional.empty();
    }

    @Override
    public Optional<Group> deleteById(Long id) {
        Optional<Group> optionalGroup = findById(id);

        if(optionalGroup.isEmpty()){
            System.out.println("(UPDATE)Student with id " + id + " does note exist");
            return optionalGroup;
        }


        Session session = SessionUtil.createSession();
        Transaction transaction = session.beginTransaction();

        session.delete(optionalGroup.get());

        transaction.commit();
        session.close();
        return optionalGroup;
    }
}
