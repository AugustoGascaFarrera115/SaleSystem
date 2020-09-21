/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import data.interfaces.SimpleCrudInterface;
import database.DBConnection;
import entities.Category;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Felipe Gasca
 */
public class CategoryDAO implements SimpleCrudInterface<Category> {

    private final DBConnection CONNECTION;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean answer;
    
    public CategoryDAO()
    {
        CONNECTION = DBConnection.getInstance();
    }
    
    @Override
    public List<Category> toList(String text) {
        List<Category> registers = new ArrayList();
        
        try {
            
            ps = CONNECTION.connect().prepareStatement("SELECT * FROM category WHERE name LIKE ?");
            ps.setString(1,"%" + text+ "%");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                registers.add(new Category(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
            }
            
            ps.close();
            rs.close();
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps = null;
            rs = null;
            
            CONNECTION.disconnect();
        }
        
        return registers;
    }

    @Override
    public boolean insert(Category object) {
       
        answer = false;
        
        try {
            
            ps = CONNECTION.connect().prepareStatement("INSERT INTO category (name,description,.active) VALUES (?,?,1)");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            
            if(ps.executeUpdate() > 0)
            {
                answer = true;
            }
            
            ps.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            ps = null;
            CONNECTION.disconnect();
        }
        
        return answer;
        
    }

    @Override
    public boolean update(Category object) {
        answer = false;
 
        try {
            
            ps = CONNECTION.connect().prepareStatement("UPDATE category SET name=?,description=? WHERE id=?");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.setInt(3,object.getId());
            
            if(ps.executeUpdate() > 0)
            {
                answer = true;
            }
            
            ps.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            ps = null;
            CONNECTION.disconnect();
        } 
        return answer;
    }

    @Override
    public boolean deactivate(int id) {
        answer = false;
 
        try {
            
            ps = CONNECTION.connect().prepareStatement("UPDATE category SET active=0 WHERE id=?");
            ps.setInt(1,id);
            
            if(ps.executeUpdate() > 0)
            {
                answer = true;
            }
            
            ps.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            ps = null;
            CONNECTION.disconnect();
        } 
        return answer;
    }

    @Override
    public boolean activate(int id) {
        answer = false;
 
        try {
            
            ps = CONNECTION.connect().prepareStatement("UPDATE category SET active=1 WHERE id=?");
            ps.setInt(1,id);
            
            if(ps.executeUpdate() > 0)
            {
                answer = true;
            }
            
            ps.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            ps = null;
            CONNECTION.disconnect();
        } 
        return answer;
    }

    @Override
    public int total() {
        
        int totalRegisters = 0;
 
        try {
            
            ps = CONNECTION.connect().prepareStatement("SELECT COUNT(ID) FROM category");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                totalRegisters = rs.getInt("COUNT(1)");
            }
          
            ps.close();
            rs.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            ps = null;
            rs = null;
            CONNECTION.disconnect();
        } 
        return totalRegisters;
    }

    @Override
    public boolean exist(String text) {
        answer = false;
 
        try {           
            ps = CONNECTION.connect().prepareStatement("SELECT name FROM category WHERE name=?");
            ps.setString(1, text);
            rs = ps.executeQuery();           
            rs.last();
            
            if(rs.getRow() > 0)
            {
                answer = true;
            }
          
            ps.close();
            rs.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }finally{
            ps = null;
            rs = null;
            CONNECTION.disconnect();
        } 
        return answer;
    }
    
}
