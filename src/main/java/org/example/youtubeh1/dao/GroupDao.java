package org.example.youtubeh1.dao;

import org.example.youtubeh1.model.persistance.Group;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface GroupDao extends GenericDao<Group,Long>{

    OptionalInt countStudentsInGroup(Long id);
    List<Group> findWhereCountStudentsEqualsThenN(int n);
    Optional<Group> findByGroupIdWithStudents(long n);
}
