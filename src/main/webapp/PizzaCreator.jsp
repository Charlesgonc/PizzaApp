

<%@page import="oe.pizzaapp.model.Pizza"%>
<%@page import="oe.pizzaapp.model.Topping"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza Creator</title>
    </head>
    <body>
        <h1>Choose your toppings!</h1>
        <%
            ArrayList<Topping> toppings=(ArrayList<Topping>)getServletContext().getAttribute("availableToppings");
            Pizza pizza=(Pizza)session.getAttribute("pizza");
        %>
        <p>Available toppings</p>
        <table border="1">
            <% for (Topping topping : toppings) { %>
            <tr>
                <td><%=topping.getName()%></td>
                <td><%=topping.getPrice()%> HUF</td>
                <td><a href="ManageToppings?add=<%=topping.getName()%>">Add</a></td>
            </tr>
            <% } %>
        </table>
        <%
            if (request.getAttribute("message")!=null){
        %>
        <p style="font-weight: bold"><%=request.getAttribute("message") %></p>
        <% } %>
        <p>Selected toppings</p>
        <table border="1">
            <% for (Topping topping : pizza.getToppings()) { %>
            <tr>
                <td><%=topping.getName()%></td>
                <td><%=topping.getPrice()%> HUF</td>
                <td><a href="ManageToppings?remove=<%=topping.getName()%>">Remove</a></td>
            </tr>
            <% } %>
        </table>
        <p>Total price: <%=pizza.totalPrice() %> HUF</p>
        <h2>Finalize your order</h2>
        <form action="Summary.jsp" method="post">
            Name: <input type="text" name="name"/><br/>
            Address: <input type="text" name="address"/><br/>
            <input type="submit" value="Order"/>
        </form>
    </body>
</html>
