/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.interfaces;

import java.util.List;

/**
 *
 * @author Felipe Gasca
 */
public interface SimpleCrudInterface<T> {
    
    public List<T> toList(String text);
    
    public boolean insert(T object);
    
    public boolean update(T object);
    
    public boolean deactivate(int id);
    
    public boolean activate(int id);
    
    public int total();
    
    public boolean exist(String text);
}
