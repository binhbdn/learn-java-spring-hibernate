package vn.hvcg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HelloJSP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", req.getParameter("name"));

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Nguyen Van A - from ArrayList"));
        students.add(new Student(2, "Nguyen Van B - from ArrayList"));

        // Comment out for other solution
        req.setAttribute("students", students);
        resp.setStatus(200);
        req.getRequestDispatcher("/other.jsp").forward(req, resp);

        // Start Other solution
//        PrintWriter out = resp.getWriter();
//        out.println("<html><body><table><thead><tr><th>Id</th><th>Name</th></tr></thead><tbody>");
//        for (Student student:students) {
//            out.println(String.format("<tr><td>%d</td><td>%s</td></tr>",student.getId(),student.getName()));
//        };
//        out.println("</tbody></table></body></html>");
        // End Other solution
    }
}
