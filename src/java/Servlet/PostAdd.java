
package Servlet;
import ControlerBean.PostDataBean;
import FilterClass.StringFilter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Supun Randunu
 */
@MultipartConfig(maxFileSize=1024*1024*5)
public class PostAdd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    private static final String SAVE_DIR="images";
    
    private PostDataBean add=new PostDataBean();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out=response.getWriter();
        String name=request.getParameter("title");
        name=new StringFilter().filter(name); 
        String price=request.getParameter("price");
        price=new StringFilter().filter(price); 
        String description=request.getParameter("description");
        description=new StringFilter().filter(description); 
        String category=request.getParameter("Acce");
        category=new StringFilter().filter(category); 
        try{
        final Part filePart = request.getPart("pic");
        add.postData(name, price, description, category, filePart);
        }catch(IOException | ServletException e){
            out.println(e);
        }
        response.sendRedirect("product.jsp");
}  
        
    @Override
    public String getServletInfo() {
        return "Short description";
    }
   
  
    
}
