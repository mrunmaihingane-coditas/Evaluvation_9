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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SaveProduct")
public class SaveProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class);

        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String qty = request.getParameter("qty");

        HttpSession session1 = request.getSession(false);
        int userFetchId = (int) session1.getAttribute("userfetchid");

        Customer user = session.get(Customer.class, userFetchId);
        Product product = new Product(name, price, qty, user);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        user.setProductList(productList);

        session.save(product);
        session.save(user);

        transaction.commit();
        session.close();

        response.sendRedirect("Customerlogin");
    }
}
