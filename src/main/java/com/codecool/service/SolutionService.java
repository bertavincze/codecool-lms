package com.codecool.service;

import com.codecool.dao.database.DatabaseSolutionDao;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class SolutionService {

    private final DatabaseSolutionDao solutionDao;

    public SolutionService(DatabaseSolutionDao solutionDao) {
        this.solutionDao = solutionDao;
    }


    public void addSolution(String solution_id, String userID, String title, String answer, LocalDateTime time) throws SQLException {

        try {
            solutionDao.addSolution(solution_id, userID, title, answer, time); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateSolution(String solution_id, int currGrade) throws SQLException {

        try {
            solutionDao.updateSolution(solution_id, currGrade); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
