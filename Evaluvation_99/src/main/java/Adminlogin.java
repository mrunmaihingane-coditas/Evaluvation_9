import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/Adminlogin")
public class Adminlogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //servalet
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        //hibernet
        Configuration configuration= new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class).addAnnotatedClass(Product.class);
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction =session.beginTransaction();
        out.println("ADMIN LOGIN");
        out.println("<br>");
        out.println("<button type=button onclick=location.href='regestrtion.jsp'>Register Customer</button>\n");
        out.println("<br>");
        List<Customer>userlist = session.createQuery("from Customer").getResultList();
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th></th><th>Delete</th><th>Edit</th></tr>");
        for (Customer user : userlist) {
            out.println("<tr>");
            out.println("<td>" + user.getCustid() + "</td>");
            out.println("<td>" + user.getUsername() + "</td>");

            out.println("<td><a href='deleteUser?id3=" + user.getCustid() + "'>Delete</a></td>");
//
            out.println("<td><a href='edit.jsp?id4=" + user.getCustid() + "'>Edit</a></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        transaction.commit();
        session.close();






    }
}
