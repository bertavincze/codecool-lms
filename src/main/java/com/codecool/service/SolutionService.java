package com.codecool.service;

import com.codecool.dao.database.DataBaseSolutionDao;
import com.codecool.dao.database.DatabaseUserDao;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class SolutionService {

    private final DataBaseSolutionDao solutionDao;

    public SolutionService(DataBaseSolutionDao solutionDao) {
        this.solutionDao = solutionDao;
    }


    public void addSolution(String solution_id, String userID, String title, String answer, LocalDateTime time) throws SQLException {

        try {
            solutionDao.addSolution(solution_id, userID, title, answer, time); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
