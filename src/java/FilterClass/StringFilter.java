/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilterClass;

/**
 *
 * @author Supun Randunu
 */
public class StringFilter {
    public static String filter(String input) {
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for(int i=0; i<input.length(); i++) {
            c = input.charAt(i);
            
            if (c == '<') { filtered.append("&lt;"); }
            else if (c == '>') { filtered.append("&gt;"); }
            else if (c == '"') { filtered.append("&quot;"); }
            else if (c == '&') { filtered.append("&amp;"); }
            else { filtered.append(c); }
        }
        return(filtered.toString());
    }

}
