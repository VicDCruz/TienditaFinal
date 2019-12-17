/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstejbalmacen;

import java.math.BigDecimal;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import ws.Product;
import ws.WSAlmacen;
import ws.WSAlmacen_Service;

/**
 *
 * @author marti
 */
public class TstEJBAlmacen {
    
    private static final int TOTALSERVICES = 8;
    private static final int TOTALTDC = 16;
    private static final int TOTALCUSTOMERS = 16;
    private static final int MONTOMAX = 5000;
    private static final int LDCMAX = 7500;
    
    private static int getOperation() {
        return 1 + ((int) Math.round(Math.random() * (TOTALSERVICES - 1)));
    }

    public static String doRequest(WSAlmacen port) {
        
        String res = null;

        switch (getOperation()) {
            case 1:
                res = "total de productos: " + port.count() + " elementos";
                break;

            case 2:
                try {
                    List<Product> productos = port.findAll();
                    res = "Se encontro un total de " + productos.size() + " productos";
                } catch (Exception e) {
                    res = "Error al encontrar los productos";
                }
                break;

            case 3:
                try {
                    List<Product> productos = port.findAll();
                    int id_producto = productos.get(0).getProductId();
                    res = "Monto de venta: " + port.montoVtayDescuentaExistencia(id_producto, 1);
                } catch (Exception e) {
                    res = "Error al encontrar los productos";
                }
                break;
            case 4:
                try {
                    List<Product> productos = port.findAll();
                    int id_producto = productos.get(0).getProductId();
                    boolean aumento = port.reverseaDescuentaExistencia(id_producto, 1);
                    if(aumento){
                        res = "Se aumentó la cantidad del producto " + id_producto;
                    }else{
                        res = "El producto no se encontró";
                    }
                    res = "Monto de venta: " + port.montoVtayDescuentaExistencia(id_producto, 1);
                } catch (Exception e) {
                    res = "Error al encontrar los productos";
                }
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        WSAlmacen_Service service = new ws.WSAlmacen_Service();
        WSAlmacen port = service.getWSAlmacenPort();
        for (int i = 0; i < 10; i++) {
            System.out.println("Resultado de la operación: " + TstEJBAlmacen.doRequest(port));
        }
        
    }

   
    
    
}
