/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public abstract class Record {

    abstract public boolean addNewRecord();
    abstract public boolean updateRecord();
    abstract public boolean deleteRecord();
    abstract public ArrayList getAllRecord();
    abstract public ArrayList getRecord();
}
