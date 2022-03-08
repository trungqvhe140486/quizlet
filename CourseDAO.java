/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Course;
import entity.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ylbee
 */
public class CourseDAO extends DAO{
    public List<Course> getCourseByAccountId(int accountid) throws SQLException   {
        
      
        ArrayList<Course> list = new ArrayList();
        String sql = "SELECT * FROM Course where accountid=?";
        ps = con.prepareStatement(sql);
          ps.setInt(1, accountid);
        rs = ps.executeQuery();
        while(rs.next()) {
            int courseid = rs.getInt("courseid");
             
            String title = rs.getString("title");
            String description = rs.getString("description");
            Boolean visibleto = rs.getBoolean("visibleto");
            accountid = rs.getInt("accountid");
            list.add(new Course(courseid, title, description, visibleto, accountid));     
        }
        return list;
       
   }
    
       public static void main(String[] args) throws SQLException {
       List<Course> courses = new CourseDAO().getCourseByAccountId(1);
           for (Course course : courses) {
               System.out.println(course);
            }
    }
     public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                courses.add(new Course(rs.getInt("courseid"), rs.getString("title"), rs.getString("description"), rs.getBoolean("visibleto"), rs.getInt("accountid")))  ;
            }
        } catch (SQLException e) {
        }
        return courses;
    }
    
    
}
