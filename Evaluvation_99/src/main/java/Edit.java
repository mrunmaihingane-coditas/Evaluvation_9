import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet("/edit")
public class Edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Configuration configuration= new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class).addAnnotatedClass(Product.class);
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction =session.beginTransaction();


        String  name =req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        int id4 = Integer.parseInt(req.getParameter("id4"));

        Customer Updatedcustomer =new Customer(id4,name,username,password);

        Customer user=session.get(Customer.class,id4);
        user.setCustid(Updatedcustomer.getCustid());
        user.setCustname(Updatedcustomer.getCustname());
        user.setUsername(Updatedcustomer.getUsername());
        user.setPassword(Updatedcustomer.getPassword());

        session.update(user);



        resp.sendRedirect("Adminlogin");
        transaction.commit();
        session.close();
    }
}
