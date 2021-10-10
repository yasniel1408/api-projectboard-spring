package com.infsoft.projectboard.web;

import com.infsoft.projectboard.domain.ProjectTask;
import com.infsoft.projectboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("/task")
    public ResponseEntity<?> addOrUpdateProjectTaskToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error: result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        ProjectTask newProjectTask = projectTaskService.saveOrUpdateProjectTask(projectTask);
        return new ResponseEntity<ProjectTask>(newProjectTask, HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public Iterable<ProjectTask> getAllProjectTask(){
        return  projectTaskService.findAllProjectTask();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getProjectTaskById(@PathVariable Long id){
        Optional<ProjectTask> projectTask = projectTaskService.findByIdProjectTask(id);
        if(projectTask.isPresent()){
            return new ResponseEntity<ProjectTask>(projectTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteProjectTaskById(@PathVariable Long id){
        projectTaskService.deleteByIdProjectTask(id);
        return new ResponseEntity<String>("Project Task Deleted", HttpStatus.OK);
    }

}
