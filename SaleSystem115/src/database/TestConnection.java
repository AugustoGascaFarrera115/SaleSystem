/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Felipe Gasca
 */
public class TestConnection {
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        db.connect();
        
        if(db.connection != null)
        {
            System.out.println("Connected Succesfully :)))");
        }else
        {
            System.out.println("Error During Connection :(");
        }
    }
 
}
