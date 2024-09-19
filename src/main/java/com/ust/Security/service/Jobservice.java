package com.ust.Security.service;

import com.ust.Security.model.Job;
import com.ust.Security.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Jobservice {
    @Autowired
    private JobRepository jobRepo;

    public Job saveJob(Job job) {
        return jobRepo.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

}
