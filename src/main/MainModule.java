package main;

import dao.JobListingDAO;
import dao.JobListingDAOImpl;
import entity.JobListing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JobListingDAO jobListingDAO = new JobListingDAOImpl();

        while (true) {
            System.out.println("\n--- CareerHub Job Board ---");
            System.out.println("1. Post a Job");
            System.out.println("2. View All Jobs");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    JobListing job = new JobListing();

                    System.out.print("Enter Company ID: ");
                    job.setCompanyID(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter Job Title: ");
                    job.setJobTitle(sc.nextLine());

                    System.out.print("Enter Job Description: ");
                    job.setJobDescription(sc.nextLine());

                    System.out.print("Enter Job Location: ");
                    job.setJobLocation(sc.nextLine());

                    System.out.print("Enter Salary: ");
                    job.setSalary(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Enter Job Type (Full-time / Part-time / Contract): ");
                    job.setJobType(sc.nextLine());

                    job.setPostedDate(LocalDateTime.now()); // set current date-time

                    jobListingDAO.insertJob(job);
                    System.out.println("Job posted successfully!");
                    break;

                case 2:
                    List<JobListing> jobs = jobListingDAO.getAllJobs();
                    if (jobs.isEmpty()) {
                        System.out.println("No jobs posted yet.");
                    } else {
                        for (JobListing j : jobs) {
                            System.out.println("Job ID: " + j.getJobID());
                            System.out.println("Company ID: " + j.getCompanyID());
                            System.out.println("Title: " + j.getJobTitle());
                            System.out.println("Description: " + j.getJobDescription());
                            System.out.println("Location: " + j.getJobLocation());
                            System.out.println("Salary: " + j.getSalary());
                            System.out.println("Job Type: " + j.getJobType());
                            System.out.println("Posted Date: " + j.getPostedDate());
                            System.out.println("----------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using CareerHub! Goodbye.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        }
    }
}

