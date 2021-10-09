package com.infsoft.projectboard.service;

import com.infsoft.projectboard.domain.ProjectTask;
import com.infsoft.projectboard.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask){

        if(projectTask.getStatus() == null || projectTask.getStatus() == "" ){
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findAllProjectTask(){
        return projectTaskRepository.findAll();
    }

    public Optional<ProjectTask> findByIdProjectTask(Long id){
        return projectTaskRepository.findById(id);
    }
}
