package dao;

import entity.JobApplication;
import util.DBconnUtil; // updated import

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAOImpl implements JobApplicationDAO {

    @Override
    public void insertApplication(JobApplication application) {
        String sql = "INSERT INTO JobApplication (JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBconnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, application.getJobID());
            pstmt.setInt(2, application.getApplicantID());
            pstmt.setTimestamp(3, Timestamp.valueOf(application.getApplicationDate()));
            pstmt.setString(4, application.getCoverLetter());

            pstmt.executeUpdate();
            System.out.println("Job application submitted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<JobApplication> getApplicationsForJob(int jobID) {
        List<JobApplication> applications = new ArrayList<>();
        String sql = "SELECT * FROM JobApplication WHERE JobID = ?";

        try (Connection conn = DBconnUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, jobID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                JobApplication app = new JobApplication();
                app.setApplicationID(rs.getInt("ApplicationID"));
                app.setJobID(rs.getInt("JobID"));
                app.setApplicantID(rs.getInt("ApplicantID"));
                app.setApplicationDate(rs.getTimestamp("ApplicationDate").toLocalDateTime());
                app.setCoverLetter(rs.getString("CoverLetter"));
                applications.add(app);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }
}
