<%-- 
    Document   : comprobarSaldo
    Created on : 16/12/2019, 09:32:44 PM
    Author     : cecij
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprobar saldo</title>
    </head>
    <body>
        <form>
            <%
                try {
                    wservices.WSPagos service = new wservices.WSPagos();
                    wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
                    // TODO initialize WS operation arguments here
                    String TDC;
                    double dblMonto = 500000000;
                    int intTDC;
                    // TODO process result here
                    TDC= request.getParameter("dropdown");
                    intTDC = Integer.parseInt(TDC);
                    boolean result = port.pagoConTDC(intTDC, dblMonto);
                    if(result!=true){
                       
                       response.sendRedirect("sinSaldo.jsp");;
                    }
                    else{
                        response.sendRedirect("envios.jsp");
                    }
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
            %>
        </form>


    </body>
</html>
