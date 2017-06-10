/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kagoyume;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author komoritakeshi
 */
public class BuyComplete extends HttpServlet {

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
            
            final String FORWARD_PATH = "WEB-INF/buy_complete.jsp";
            request.setCharacterEncoding("UTF-8");
            HttpSession hs = request.getSession();
            ItemDataDTO idt = new ItemDataDTO();
            ArrayList<ItemBeans> ibArray = (ArrayList<ItemBeans>)hs.getAttribute("ItemBeansBox");
            UserDataBeans loginUserBeans = (UserDataBeans)hs.getAttribute("LOGIN_USER");
            
            for (int i = 0; i < ibArray.size(); i++) {
                
                System.out.print("----------------deliveryType-------------------");
                System.out.print(request.getParameter("deliveryType"));
                System.out.print("----------------deliveryType-------------------");
                
                ibArray.get(i).setDeliveryType(Integer.parseInt(request.getParameter("deliveryType")));
                ibArray.get(i).ID2DTOMapping(idt, loginUserBeans.getUserID());
                ItemDataDAO.getInstance().insert(idt);
            }
            
            hs.removeAttribute("ItemBeansBox");
            
            RequestDispatcher rd = request.getRequestDispatcher(FORWARD_PATH);
            rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
