/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazin.atm_simulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author yazin
 */
public class Derby {
    
    private static Connection dbConnection;
    //private Statement dbStatement;
    //private boolean dbExists = false;
    
    public void connectToDB() throws SQLException {
        try {
            dbConnection = DriverManager.getConnection("jdbc:derby:ATM_DB");
            //dbExists = true;
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
            createDB();
        }

    }

    public void createDB() throws SQLException {
        //if (!dbExists) {
        dbConnection = DriverManager.getConnection("jdbc:derby:ATM_DB;create=true");
        //}
    }
    
    public void closeDBConnection() throws SQLException{
        dbConnection.close();
    }
    
}
