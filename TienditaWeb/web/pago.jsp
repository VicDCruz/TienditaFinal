<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>PAGO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <p>Selecciona una tarjeta de crédito:</p>
        <form  method="get" action="comprobarSaldo.jsp">
            <select name = "dropdown">
                <%
                    try {
                        wservices.WSPagos service = new wservices.WSPagos();
                        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
                        // TODO process result here
                        java.util.List<wservices.Tdc> result = port.findAll();
                        for (int i = 0; i < result.size() ; i++) {
                            out.println("<option>"+ result.get(i).getTdcId()+ "</option>");
                        }
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                    }
                %>
            </select>
            <br>
            <br>
            <input type="submit" name="okey" value="Pagar" >
        </form>


    </body>
</html>
