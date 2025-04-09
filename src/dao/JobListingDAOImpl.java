package dao;

import entity.JobListing;
import util.DBconnUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobListingDAOImpl implements JobListingDAO {

    @Override
    public void insertJob(JobListing jobListing) {
        String sql = "INSERT INTO JobListing (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, jobListing.getCompanyID());
            pstmt.setString(2, jobListing.getJobTitle());
            pstmt.setString(3, jobListing.getJobDescription());
            pstmt.setString(4, jobListing.getJobLocation());
            pstmt.setDouble(5, jobListing.getSalary());
            pstmt.setString(6, jobListing.getJobType());
            pstmt.setTimestamp(7, Timestamp.valueOf(jobListing.getPostedDate()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Job inserted successfully into database!");
            } else {
                System.out.println("Job insertion failed!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<JobListing> getAllJobs() {
        List<JobListing> jobs = new ArrayList<>();
        String sql = "SELECT * FROM JobListing";

        try (Connection conn = DBconnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                JobListing job = new JobListing();
                job.setJobID(rs.getInt("JobID"));
                job.setCompanyID(rs.getInt("CompanyID"));
                job.setJobTitle(rs.getString("JobTitle"));
                job.setJobDescription(rs.getString("JobDescription"));
                job.setJobLocation(rs.getString("JobLocation"));
                job.setSalary(rs.getDouble("Salary"));
                job.setJobType(rs.getString("JobType"));
                job.setPostedDate(rs.getTimestamp("PostedDate").toLocalDateTime());

                jobs.add(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobs;
    }
}

