import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Customerlogin")
public class Customerlogin extends HttpServlet {
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

        HttpSession session1=req.getSession(false);
        int userfetchid = (int) session1.getAttribute("userfetchid");
        System.out.println(userfetchid+"fetchid");
        out.println("WELCOME USER ID 1:  "+userfetchid);
        out.println("<br>");
        out.println("*************************MYDATA*********************************");
        out.println("<br>");

        Query q =session.createQuery("from Customer u where u.id=:id ");
        q.setParameter("id",userfetchid);
        List<Customer> userlist = q.getResultList();

        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>UserName</th></tr>");
        for (Customer user : userlist) {
            out.println("<tr>");
            out.println("<td>" + user.getCustid() + "</td>");
            out.println("<td>" + user.getCustname() + "</td>");
            out.println("<td>" + user.getUsername() + "</td>");

            out.println("</tr>");
        }
        out.println("</table>");

        out.println("<a href='Product.jsp'>Add a Product</a>");
        out.println("<Br>");
        out.println("<a href='Bill'> genrate bill</a>");

        out.println();


        out.println("************************************MY PRODUCTS**********************************************");

        Query q1 =session.createQuery("from Product  where customer_custid=:id ");
        q1.setParameter("id",userfetchid);
        List<Product> productList = q1.getResultList();

        System.out.println("in list product of customer");

        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Price</th><th>Quantity</th><th>Delete</th></tr>");
        for (Product p : productList) {
            out.println("<tr>");
            out.println("<td>" + p.getId()+ "</td>");
            out.println("<td>" + p.getName() + "</td>");
            out.println("<td>" + p.getPrice() + "</td>");
            out.println("<td>" + p.getQty()+ "</td>");
            out.println("<td><a href='deleteProduct?id5=" + p.getId() + "'>Delete</a></td>");
            out.println("</tr>");
        }
        out.println("</table>");




        transaction.commit();;
        session.close();
    }
}
