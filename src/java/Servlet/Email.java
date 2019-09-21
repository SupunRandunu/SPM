
package Servlet;

import Conn.ConnectDb;
import FilterClass.StringFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Supun Randunu
 */
public class Email extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
         Connection conn=ConnectDb.getConnection();
        int val1 = 0;
        int val2 = 0;
        try{
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            Statement stmt = conn.createStatement();
            String sql="SELECT `month`, `val1`, `val2`, `num` FROM `recap` WHERE `month`='"+month+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                val1=rs.getInt("val1");
                val2=rs.getInt("val2");
       
            }
            val1=val1+1;
            val2=val2+2;
            String sqlInset="UPDATE `recap` SET `val1`="+val1+",`val2`="+val2+" WHERE `month`='"+month+"'";
            stmt.executeUpdate(sqlInset);
            
        }catch(Exception e){
            
        }
        
        
         try{
            String title=request.getParameter("title");
            title=new StringFilter().filter(title); 
            String email=request.getParameter("email");
            email=new StringFilter().filter(email); 
            String host ="smtp.gmail.com" ;
            String user = "randunu367@gmail.com";
            String pass = "gold pioneer";
            String to = "kwsrkarunarathne@students.nsbm.lk";
            String from = "randunu367@gmail.com";
            String subject = "Complete your purchase:"+title+"";
            String messageText = "Your Is Test Email :";
            boolean sessionDebug = false;
           
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           response.sendRedirect("PaymentGateway.html");
        }catch(Exception ex)
        {
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
