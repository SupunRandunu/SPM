
package Servlet;

import ControlerBean.AddToCartBean;
import FilterClass.StringFilter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Supun Randunu
 */
public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
//        name=new StringFilter().filter(name); 
        String price=request.getParameter("price");
//        price=new StringFilter().filter(price);
        String image=request.getParameter("image");
        
        String id=request.getParameter("id");
//        id=new StringFilter().filter(id);
        
       
        
        HttpSession session = request.getSession();
        String email=(String) session.getAttribute("email");
      
            //out.println(name+""+price+""+image);
            AddToCartBean obj=new AddToCartBean();
            obj.addToCart(email, name, image, price,id);
        
            response.sendRedirect("product.jsp");
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
