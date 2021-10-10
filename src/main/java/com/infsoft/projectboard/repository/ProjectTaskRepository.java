package com.infsoft.projectboard.repository;

import com.infsoft.projectboard.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
