package com;



import java.sql.*;
import java.util.*;

public class StudentDao {

    Connection connection = null;

    void Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String mysqlURL = "jdbc:mysql://localhost:3306/test";
            connection = DriverManager.getConnection(mysqlURL, "root", "");
        } catch (Exception ex) {
            System.out.println("I m here 4" + ex.toString());
        }
    }

    public int Insert(Student s) 
    {
        
        Connection();
        int res = 0;
        try
        {
            String qry = "insert into tblStudent values (?,?,?)";

            PreparedStatement pst = connection.prepareStatement(qry);

            pst.setInt(1, s.Id);
            pst.setString(2, s.Name);
            pst.setString(3, s.Degree);
            
            

            res = pst.executeUpdate();
        }
        catch(Exception ex)
        {
            System.out.println("I m here 3" + ex.toString());
        }
        return res;
    }
    public int Update(Student s) 
    {
        Connection();
        int res = 0;
        try
        {
            String qry = "update tblStudent set Name = ?, Degree = ? where Id = ?";

            PreparedStatement pst = connection.prepareStatement(qry);

            
            pst.setString(1, s.Name);
            pst.setString(2, s.Degree);
            pst.setInt(3, s.Id);
            

            res = pst.executeUpdate();
        }
        catch(Exception ex)
        {
            
        }
        return res;
    }
    
    public int Delete(Student s) 
    {
        Connection();
        int res = 0;
        try
        {
            String qry = "delete from tblstudent where Id = ?";

            PreparedStatement pst = connection.prepareStatement(qry);

            
            
            pst.setInt(1, s.Id);
            

            res = pst.executeUpdate();
        }
        catch(Exception ex)
        {
            
        }
        return res;
    }
    
    public ArrayList<Student> SelectAll()
    {
        ArrayList<Student> list = new ArrayList<Student>();
        Connection();
        try
        {
            String qry = "Select * from tblStudent";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(qry);
            
            while (rs.next())
            {
                Student s = new Student();
                s.Id = Integer.parseInt(rs.getString(1));
                s.Name = rs.getString(2);
                s.Degree = rs.getString(3);
                list.add(s);
                
            }
        }
        catch(Exception ex)
        {
            
        }
        return list;
    }
    public Student SelectById(Student s)
    {
        ResultSet rs = null;
        Student s1 = new Student();
        Connection();
        try
        {
            String qry = "Select * from tblStudent where Id = ?";
            PreparedStatement pst = connection.prepareStatement(qry);
            pst.setInt(1, s.Id);
            rs = pst.executeQuery();
            while (rs.next())
            {
                s.Id = Integer.parseInt(rs.getString(1));
                s.Name = rs.getString(2);
                s.Degree = rs.getString(3);
            }
        }
        catch(Exception ex)
        {
            
        }
        return s1;
    }
    
    

}

