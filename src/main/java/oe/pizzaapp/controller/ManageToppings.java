/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oe.pizzaapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oe.pizzaapp.model.Pizza;
import oe.pizzaapp.model.Topping;


@WebServlet(name = "ManageToppings", urlPatterns = {"/ManageToppings"})
public class ManageToppings extends HttpServlet {

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
       //If available toppings are not set yet in the application scope then do it now
        if (getServletContext().getAttribute("availableToppings")==null){
                ArrayList<Topping> toppings=new ArrayList();
                toppings.add(new Topping("mushrooms",100));
                toppings.add(new Topping("tomatoes",150));
                toppings.add(new Topping("beans",150));
                toppings.add(new Topping("broccoli",200));
                toppings.add(new Topping("corn",100));
                toppings.add(new Topping("cheddar cheese",300));
                toppings.add(new Topping("mozzarella cheese",200));
                toppings.add(new Topping("parmesan cheese",300));
                
                getServletContext().setAttribute("availableToppings",toppings);
        }
        Pizza pizza=(Pizza)request.getSession().getAttribute("pizza");
        ArrayList<Topping> toppings=(ArrayList<Topping>)getServletContext().getAttribute("availableToppings");
        // A topping has to be added to the pizza
        if (request.getParameter("add")!=null){
            Topping t=toppings.stream()
                    .filter(topping->topping.getName().equals(request.getParameter("add")))
                    .findFirst().orElse(null);
            if (t!=null){
                pizza.addTopping(t);
                request.setAttribute("message", "Topping added.");
            }
        }
        // A topping has to be removed from the pizza
        if (request.getParameter("remove")!=null){
            Topping t=toppings.stream()
                    .filter(topping->topping.getName().equals(request.getParameter("remove")))
                    .findFirst().orElse(null);
            if (t!=null){
                pizza.removeTopping(t);
                request.setAttribute("message", "Topping removed.");
            }
        }
        // Forward to the creator page
        request.getRequestDispatcher("PizzaCreator.jsp").forward(request, response);
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
