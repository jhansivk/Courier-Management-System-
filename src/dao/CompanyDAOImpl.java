package dao;

import entity.Company;
import util.DBconnUtil; // updated import

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public void insertCompany(Company company) {
        String sql = "INSERT INTO Company (CompanyName, Location) VALUES (?, ?)";

        try (Connection conn = DBconnUtil.getConnection(); // updated usage
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, company.getCompanyName());
            pstmt.setString(2, company.getLocation());

            pstmt.executeUpdate();
            System.out.println("Company registered successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM Company";

        try (Connection conn = DBconnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Company company = new Company();
                company.setCompanyID(rs.getInt("CompanyID"));
                company.setCompanyName(rs.getString("CompanyName"));
                company.setLocation(rs.getString("Location"));
                companies.add(company);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }
}
