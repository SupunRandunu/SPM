
package Servlet;

import ControlerBean.AdminLoginBean;
import ControlerBean.LoginBean;
import FilterClass.StringFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Supun Randunu
 */
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    LoginBean user=new LoginBean();
    AdminLoginBean admin=new AdminLoginBean();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        String encodings = request.getHeader("Accept-Encoding");
        String encodeFlag = request.getParameter("encoding"); 
       	PrintWriter out; 
	String title;
      
        if ((encodings != null) && (encodings.indexOf("gzip") != -1) && !"none".equals(encodeFlag)) {     			
                title = "Page Encoded with GZip";
      		OutputStream out1 = response.getOutputStream();
      		out = new PrintWriter(new GZIPOutputStream(out1), false);
      		response.setHeader("Content-Encoding", "gzip");
 	} else {
     		title = "Unencoded Page";
      		out = response.getWriter();
  	}
        
        String email=request.getParameter("name");
        email=new StringFilter().filter(email); 
        String pass=request.getParameter("pass");
        pass=new StringFilter().filter(pass); 
        boolean result=user.login(email,pass);
        out.println(result);
        boolean adm=admin.adminLogin(email, pass);
        
          if(adm==true){
              
            HttpSession session = request.getSession(true);
            session.setAttribute("adminEmail", email);
            response.sendRedirect("http://localhost:8080/DEAAssignment/AdminLTE-master/index2.html");
        }
        
            else if(result==true){
            HttpSession session = request.getSession(true);
            session.setAttribute("email", email);
            response.sendRedirect("index.jsp");
        }
        else{
            response.sendRedirect("Login.jsp");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
