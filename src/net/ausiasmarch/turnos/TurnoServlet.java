/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.turnos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author <a href="mailto:logongas@users.sourceforge.net">Lorenzo González</a>
 */
public class TurnoServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        ServletOutputStream salida = res.getOutputStream();

        res.setContentType("text/html");

        String turno = req.getParameter("turno");
        ArrayList<String> turnos=new ArrayList<String>();
        if ((turno==null) || (turno.trim().length()==0)) {
            Iterator<String> it = Turno.estados.keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                turnos.add(name);
            } 
        } else {
            turnos.add(turno);
        }
        
        
        int fontSize = 0;

        try {
            fontSize = Integer.parseInt(req.getParameter("size"));
        } catch (NumberFormatException ex) {
            fontSize = 0;
        }
        if (fontSize == 0) {
            fontSize = 30;
        }

        String style = "text-align:center;padding:5px;background-color:#EEEEEE;border-top: 1px solid #FFFFFF;border-left: 1px solid #FFFFFF;border-right: 1px solid #747474; border-bottom: 1px solid #747474; font-size:" + fontSize + "px; font-family: Verdana, Arial, Helvetica, sans-serif;";

        salida.println("<html>");
        salida.println("	<head>");
        salida.println("	<meta http-equiv=\"refresh\" content=\"10\" />  ");
        salida.println("	</head>");
        salida.println("	<body bgcolor=\"#FFFFFF\">");
        salida.println("	<table width=\"100%\" heght=\"100%\" >");
        for(int i=0;i<turnos.size();i++) {
            String name =turnos.get(i) ;
            Turno.Estado estado = Turno.estados.get(name);

            if (estado.visible==true) {
                salida.println("	<tr id=\"" + name + "\"><td><table style=\"" + style + "\" >");
                salida.println("            <tr><td>&nbsp;" + estado.getFormatValue() + "&nbsp;</td></tr>");
                salida.println("            <tr><td>&nbsp;" + estado.description + "&nbsp;</td></tr>");
                salida.println("        </table></td></tr>");
            }
            
        }
        salida.println("	</table>");
        salida.println("	</body>");
        salida.println("</html>");

    }
}

