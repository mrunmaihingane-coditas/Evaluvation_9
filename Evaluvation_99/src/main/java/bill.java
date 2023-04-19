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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@WebServlet("/Bill")
public class bill extends HttpServlet {
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
        out.println("WELCOME USER ID  :  "+userfetchid);


        Query q =session.createQuery("from Product  where customer_custid=:id ");
        q.setParameter("id",userfetchid);
        List<Product> userlist = q.getResultList();

        int totalPrice=0;


        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Email</th></tr>");
        for (Product user : userlist) {
            out.println("<tr>");
            out.println("<td>" + user.getId()+ "</td>");
            out.println("<td>" + user.getName() + "</td>");
            out.println("<td>" + user.getPrice() + "</td>");
            out.println("<td>" + user.getQty()+ "</td>");
            out.println("</tr>");
            totalPrice += Integer.parseInt(user.getPrice()) * Integer.parseInt(user.getQty());



        }
        out.println("</table>");


        out.println("<Br>");

        out.println("USER ID : "+userfetchid+" TOTAL BILL "+totalPrice);
        // Get the current date and time
        Date currentDate = new Date();

       // Format the date and time string
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        // Display the date and time after the total amount
        out.println("<br>");
        out.println("Date and time: " + formattedDate);

        out.println();

        out.println("<a href ='Logout'>logout</a>");

        transaction.commit();;
        session.close();



    }
}
