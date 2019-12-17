<%-- 
    Document   : envios
    Created on : 16/12/2019, 08:22:06 PM
    Author     : vicda
--%>

<%@page import="envios.Envio"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            int numEntrega = 2000 + ((int) Math.round(Math.random() * (1000 - 1)));
            int numCompra = 1556 + ((int) Math.round(Math.random() * (2000 - 1)));
            int usuarioId = 1;
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 4);
            c.add(Calendar.HOUR, 6);
            c.add(Calendar.MINUTE, 30);
            Date date = c.getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String strDate = dateFormat.format(date);
            
            Envio e = new Envio(date, usuarioId, numEntrega, numCompra);
            e.crearEnvio();
        %>
        <h1>Se va a realizar una venta - # <%=numCompra%> </h1>
        <h1>Número de entrega - # <%=numEntrega%> </h1>
        <p> Pedido exitoso! </p>
        <p> Fecha estimada de entrega - <%=strDate%> </p>
    </body>
</html>
