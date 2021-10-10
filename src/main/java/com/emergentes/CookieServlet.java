package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = null;
        int visitas = 0;
        int contador = 1;
        int count = 0;
        boolean nuevaVisita = true;
        //Obtener el arreglo de cookies
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ((c.getName().equals("visitante")) && c.getValue().equals("1")){
                    nuevaVisita = false;
                    break;
                }
            }
        }   
       
        
        if (nuevaVisita) {
            Cookie ck = new Cookie("visitante", "1");
            ck.setMaxAge(120);
            ck.setComment("Control de nuevos Visitantes");
            response.addCookie(ck);
            mensaje = "Gracias por Visitar Nuestra Pagina";
        }else{
            Cookie ckk = new Cookie("visitante", "3");
            count = Integer.parseInt(ckk.getValue());
            ckk.setValue(Integer.toString(count+1));
            ckk.setMaxAge(120);
            response.addCookie(ckk);
                    
//            PrintWriter out = response.getWriter();
//            Cookie c[]=request.getCookies(); 
            //c.length gives the cookie count 
//            for(int i=0;i<c.length;i++){
//                count++;
//                out.println("Name: "+c[i].getName()+" & Value: "+c[i].getValue());
//                out.println("contador:"+count);
//            }
//  
                
//                count++;
            
            mensaje = "Estamos Agradecidos por Tenerlo Nuevamente";
            PrintWriter out = response.getWriter();
            out.println("<h1>"+"Numero de Visitas : "+ ckk.getValue() + "</h1>");
            
            
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>"+ mensaje + "</h1>");

        out.println("<a href='index.jsp'>IR al Inicio</a>");
    }
}
