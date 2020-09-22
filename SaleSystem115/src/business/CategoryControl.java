/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.CategoryDAO;
import entities.Category;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Felipe Gasca
 */
public class CategoryControl {
    
    private final CategoryDAO DATA;
    private final Category OBJECT;
    private DefaultTableModel tableModel;
    private int registersShown;
    
    public CategoryControl()
    {
        this.DATA = new CategoryDAO();
        this.OBJECT = new Category(); 
        this.registersShown = 0;
    }
    
    public DefaultTableModel toList(String text)
    {
        List<Category> categoriesList = new ArrayList();
        categoriesList.addAll(DATA.toList(text));
        
        String[] headers = {"Id","Name","Description","State"};
        this.tableModel = new DefaultTableModel(null, headers);
        
        String state;
        String[] register = new String[4];
        
        this.registersShown = 0;
        
        
        for (Category item : categoriesList) {
            
            if(item.isActive())
            {
                state = "Active";
            }else{
            
                state = "Inactive";
            }
            
            register[0] = Integer.toString(item.getId());
            register[1] = item.getName();
            register[2] = item.getDescription();
            register[3] = state;
            this.tableModel.addRow(register);
            this.registersShown = this.registersShown+1;
        }
        return this.tableModel;      
    }
    
    public String insert(String name,String description)
    {
        if(DATA.exist(name))
        {
            return "The register already exist";
        }else{
            OBJECT.setName(name);
            OBJECT.setDescription(description);
            
            if(DATA.insert(OBJECT))
            {
                return "INSERTED SUCCESFULLY";
            }else{
                return "INSERTION FAILED";
            }
            
        }
       
    }
    
    public String update(int id,String name,String previousName,String description)
    {
        if(name.equals(previousName))
        {
            OBJECT.setId(id);
            OBJECT.setName(name);
            OBJECT.setDescription(description);
            
            if(DATA.update(OBJECT))
            {
                return "UPDATED SUCCESFULLY";
            }else{
                return "UPDATED FAILED";
            }
            
        }else
        {
            if(DATA.exist(name))
            {
                return "The register already exist";
            }else{
                
            OBJECT.setId(id);
            OBJECT.setName(name);
            OBJECT.setDescription(description);
            
            if(DATA.update(OBJECT))
            {
                return "UPDATED SUCCESFULLY";
            }else{
                return "UPDATED FAILED";
            }
                
                
            }
        }
    }
    
    public String deactivate(int id)
    {
       if(DATA.deactivate(id))
       {
           return "OK";
       }else
       {
           return "THE REGISTER CANNOT BE DEACTIVATE";
       }
    }
    
    public String activate(int id)
    {
        if(DATA.activate(id))
        {
            return "OK";
        }else
        {
            return "THE REGISTER CANNOT BE ACTIVATE";
        }
    }
    
    public int total()
    {
        return DATA.total();
    }
    
    public int totalShown()
    {
        return this.registersShown;
    }
}
