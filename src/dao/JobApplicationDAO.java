package dao;

import entity.JobApplication;
import java.util.List;

public interface JobApplicationDAO {
    void insertApplication(JobApplication application);
    List<JobApplication> getApplicationsForJob(int jobID);
}

