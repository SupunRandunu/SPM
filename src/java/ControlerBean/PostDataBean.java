/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlerBean;

import Conn.ConnectDb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Part;

/**
 *
 * @author Supun Randunu
 */
public class PostDataBean {
     InputStream inputstream=null;
    OutputStream output = null;
    public void postData(String name,String price,String description,String category,Part filePart){
        try{
             String picName=filePart.getSubmittedFileName();
        String filedata=filePart.getContentType();
        inputstream=filePart.getInputStream();
        File f = new File(picName);      
        String dbPicName=(String)f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\")+1);
        int read = 0;
        final byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes);
        String filename=""+dbPicName;
        File targetFile = new File("C:\\xampp\\htdocs\\img\\"+dbPicName+"");
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(bytes);
        Connection conn=ConnectDb.getConnection();
        Statement stmt = conn.createStatement();
        Date date= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(date);
        String sql = "INSERT INTO `postadd`(`title`, `price`, `description`, `category`, `photo`, `date`) VALUES ('"+name+"','"+price+"','"+description+"','"+category+"','"+dbPicName+"','"+currentTime+"')";
      
        stmt.executeUpdate(sql);
        
        }catch(IOException | SQLException e){
            e.printStackTrace();
        }
    }
}
