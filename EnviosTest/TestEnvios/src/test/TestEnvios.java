/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import webservice.Registro;

/**
 *
 * @author vicda
 */
public class TestEnvios {

    private static final int TOTALSERVICES = 7;
    private static final int TOTALCUSTOMERS = 16;
    private static final int TOTALENTREGAS = 90;
    private static final int MAXLOOP = 15;
    
    private static int orden = 0;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int loop = args[1] == null ? getLoop() : Integer.parseInt(args[1]);
        System.out.println("Esta prueba realizará " + loop + " iteraciones");
        
        for (int i = 0; i < loop; i++) {
            System.out.println("Resultado " + TestEnvios.doRequest());
        }
    }

    private static int getOperation() {
        return 1 + ((int) Math.round(Math.random() * (TOTALSERVICES - 1)));
    }
    
    private static int getCustomerId() {
        return 1 + ((int) Math.round(Math.random() * (TOTALCUSTOMERS - 1)));
    }
    
    private static int getEntregaId() {
        return 1 + ((int) Math.round(Math.random() * (TOTALENTREGAS - 1)));
    }
    
    private static int getLoop() {
        return 1 + ((int) Math.round(Math.random() * (MAXLOOP - 1)));
    }
    
    private static int getOrden() {
        orden++;
        return orden;
    }
    
    private static XMLGregorianCalendar getXMLDate() {
        XMLGregorianCalendar output = null;
        
        try {
            String FORMATER = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            
            DateFormat format = new SimpleDateFormat(FORMATER);
            
            Date date = new Date();
            output =
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(TestEnvios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }

    public static String doRequest() {
        webservice.WSEnvios_Service service = new webservice.WSEnvios_Service();
        webservice.WSEnvios port = service.getWSEnviosPort();
        
        String output = null;

        switch (getOperation()) {
            case 1:
                output = "count: " + count(port) + " envios";
                break;
            case 2:
                XMLGregorianCalendar date = getXMLDate();
                
                Registro entity = new Registro();
                entity.setCustomerId(getCustomerId());
                entity.setEntregaId(getEntregaId());
                entity.setFechaEntregado(date);
                entity.setFechaPrometida(date);
                entity.setOrdenCompra(getOrden());
                entity.setStatus(1);
                try {
                    create(entity, port);
                    output = "create: Se agregó envio";
                } catch (Exception e) {
                    output = "create: NO se agregó envio";
                }
                break;
            case 3:
                XMLGregorianCalendar dateEdit = getXMLDate();
                
                Registro editR = new Registro();
                editR.setCustomerId(getCustomerId());
                editR.setEntregaId(getEntregaId());
                editR.setFechaEntregado(dateEdit);
                editR.setFechaPrometida(dateEdit);
                editR.setOrdenCompra(getOrden());
                editR.setStatus(1);
                try {
                    edit(editR, port);
                    output = "edit: Registro editado";
                } catch (Exception e) {
                    output = "edit: No se pudo editar registro";
                }
                break;
            case 4:
                try {
                    Registro r = find(getEntregaId(), port);
                    output = "find: Se encontró " + r.getEntregaId();
                } catch (Exception e) {
                    output = "find: Sin resultados";
                }
                break;
            case 5:
                try {
                    List<Registro> rAll = findAll(port);
                    output = "findAll: Se encontraron " + rAll.size() + " registros";
                } catch (Exception e) {
                    output = "findAll: Error al encontrar todos";
                }
                break;
            case 6:
                List<Integer> l = new ArrayList<>();
                for (int i = 0; i < 15; i++) {
                    int id = getEntregaId();
                    if (!l.contains(id))
                        l.add(id);
                }
                System.out.println("findRange: # Elementos a enviar - " + 
                        l.size());
                List<Registro> rAll = findRange(l, port);
                output = "findRange: Se encontraron " + 
                        rAll.size() + "/" + l.size() + "registros";
                break;
            case 7:
                Registro r = new Registro();
                int id = getEntregaId();
                r.setEntregaId(id);
                try {
                    remove(r, port);
                    output = "remove: Se removio " + id;
                } catch (Exception e) {
                    output = "remove: No se removio nada";
                }
                break;
            default:
                throw new AssertionError();
        }
        return output;
    }

    private static int count(webservice.WSEnvios port) {
        return port.count();
    }

    private static void create(webservice.Registro entity,
            webservice.WSEnvios port) {
        port.create(entity);
    }

    private static void edit(webservice.Registro entity,
            webservice.WSEnvios port) {
        port.edit(entity);
    }

    private static Registro find(int id, webservice.WSEnvios port) {
        return port.find(id);
    }

    private static List<webservice.Registro> findAll(
            webservice.WSEnvios port) {
        return port.findAll();
    }

    private static List<webservice.Registro> findRange(
            List<java.lang.Integer> range, webservice.WSEnvios port) {
        return port.findRange(range);
    }

    private static void remove(webservice.Registro entity,
            webservice.WSEnvios port) {
        port.remove(entity);
    }

}
