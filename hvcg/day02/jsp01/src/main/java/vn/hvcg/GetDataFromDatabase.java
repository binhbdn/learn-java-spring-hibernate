package vn.hvcg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromDatabase extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = new ArrayList<>();
        // Get data from Database
        Connection con = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
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


        req.setAttribute("students", students);
        resp.setStatus(200);
        req.getRequestDispatcher("/db.jsp").forward(req, resp);
    }
}
