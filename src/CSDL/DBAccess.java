/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSDL;

import java.sql.*;

/**
 *
 * @author tienn
 */
public class DBAccess {
    private Connection conn;
    private Statement state;
    public DBAccess(Connection conn) throws SQLException{
        try{
            this.conn = conn;
            state = this.conn.createStatement();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
 
    
    public int update(String update){
        try{
            int i = state.executeUpdate(update);
            return i;
        }catch(Exception e){
            return -1;
        }
    }
    
    public ResultSet truyvan(String sql){
        try{
            ResultSet rs = state.executeQuery(sql);
            return rs;
        }catch(Exception e){
            return null;
        }
    }
}
