
package Servlet;

import ControlerBean.RegisterBean;
import FilterClass.StringFilter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Supun Randunu
 */
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private RegisterBean reg=new RegisterBean();
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String admin="no";
        String name=request.getParameter("name");
        name=new StringFilter().filter(name); 
        String num=request.getParameter("phone");
        num=new StringFilter().filter(num); 
        String email=request.getParameter("email");
        email=new StringFilter().filter(email); 
        String country=request.getParameter("country");
        country=new StringFilter().filter(country); 
        String postalcode=request.getParameter("postalcode");
        postalcode=new StringFilter().filter(postalcode); 
        String pass=request.getParameter("pass");
        pass=new StringFilter().filter(pass); 
        
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        
        reg.registerUser(admin, name, num, email, country, postalcode, pass);
        
        request.setAttribute("psw", name);
        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
