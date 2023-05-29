package hu.oe.zh.controller;

import hu.oe.zh.model.TxtFile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MasodfokuServlet", value = "/MasodfokuServlet")
public class MasodfokuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float a = Float.parseFloat(request.getParameter("a"));
        float b = Float.parseFloat(request.getParameter("b"));
        float c = Float.parseFloat(request.getParameter("c"));

        double diszkriminans = b * b - 4 * a * c;
        //request.setAttribute("diszkriminans", diszkriminans);

        double gyok, gyok1, gyok2;
        String out = null;

        TxtFile file = (TxtFile) request.getServletContext().getAttribute("megoldasok");

        if (diszkriminans > 0){
            gyok1 = (-b + Math.sqrt(diszkriminans)) / (2 * a);
            gyok2 = (-b - Math.sqrt(diszkriminans)) / (2 * a);

            //2 tizedes
//            gyok1 = (double) Math.round(gyok1 * 100)/100;
//            gyok2 = (double) Math.round(gyok2 * 100)/100;

            //4 tizedes
            gyok1 = (double) Math.round(gyok1 * 10000)/10000;
            gyok2 = (double) Math.round(gyok2 * 10000)/10000;
            out = a + " * x^2 + " + b + " * x + " + c + " = 0 => x1 = " + gyok1 + ", x2 = " + gyok2 + "\n";
            file.getBufferedWriter().write(out);
            file.getBufferedWriter().flush();
            request.setAttribute("out", out);
        }
        else if (diszkriminans == 0){//egy megoldás
            gyok = -b / (2 * a);

            //2 tizedes
//            gyok = (double) Math.round(gyok * 100)/100;

            //4 tizedes
            gyok = (double) Math.round(gyok * 10000)/10000;

            out = a + " * x^2 + " + b + " * x + " + c + " = 0 => x1 = x2 = " + gyok + "\n";
            file.getBufferedWriter().write(out);
            file.getBufferedWriter().flush();
            request.setAttribute("out", out);
        }
        else
        {
            out = a + " * x^2 + " + b + " * x + " + c + " = 0 => Nincs gyök\n";
            file.getBufferedWriter().write(out);
            file.getBufferedWriter().flush();
            request.setAttribute("out", out);
        }

        request.getRequestDispatcher("eredmeny.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
