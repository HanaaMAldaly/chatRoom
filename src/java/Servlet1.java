/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.api.scripting.JSObject;

/**
 *
 * @author Hanaa
 */
@WebServlet(urlPatterns = {"/Servlet1"})
public class Servlet1 extends HttpServlet {

    public static Vector<Message> messages = new Vector<Message>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print("g");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        String key=request.getParameter("key");
        
        HttpSession session = request.getSession(false);

        String email = (String) session.getAttribute("email");
        String passowrd = (String) session.getAttribute("password");
        if(key==null){
        if (passowrd != null && email != null) {
            User user = new User(null, passowrd, email);
            user = DataBase.getUser(user);
            String name = user.getUserName();
            String content = request.getParameter("message");
            Message message = new Message(name, content);
            messages.add(message);
            out.print(buildJson());
        }}
        else{
            response.sendRedirect("login?email="+email+"&password="+passowrd);
        }
    }

    private String buildJson() {
        Gson gson = new Gson();
        ShownObject object=new ShownObject();
        object.messages=messages;
        object.users=DataBase.users;
        object.onlineUsers= DataBase.getOnlineUser();
        return gson.toJson(object);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  processRequest(request, response);

        PrintWriter out = response.getWriter();

        out.print(buildJson());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    

}
