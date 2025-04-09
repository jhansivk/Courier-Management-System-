package dao;

import entity.JobListing;
import java.util.List;

public interface JobListingDAO {
    void insertJob(JobListing jobListing);
    List<JobListing> getAllJobs();
}

