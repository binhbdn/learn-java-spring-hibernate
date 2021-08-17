package vn.hvcg.thymeleafdemo;

import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
    private static final List<Student> students = new ArrayList<>();

    static {
        Connection con = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
            Statement stat = con.createStatement();
            String query = "SELECT * FROM tbl_students";
            ResultSet resultset = stat.executeQuery(query);

            while (resultset.next()) {
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                students.add(new Student(id, name));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String studentList(Model model) {
        model.addAttribute("students", students);

        return "students";
    }

}
