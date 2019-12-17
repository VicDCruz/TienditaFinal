/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author vicda
 */
public class Envio {
    private wsenvios.Registro entity;

    public Envio(Date date, int customerId, int entregaId, int ordenId) {
        XMLGregorianCalendar shippingDate = this.getXMLDate(date);
        
        this.entity = new wsenvios.Registro();
        this.entity.setCustomerId(customerId);
        this.entity.setEntregaId(entregaId);
        this.entity.setFechaEntregado(shippingDate);
        this.entity.setFechaPrometida(shippingDate);
        this.entity.setOrdenCompra(ordenId);
        this.entity.setStatus(1);
    }
    
    private XMLGregorianCalendar getXMLDate(Date date) {
        XMLGregorianCalendar output = null;
        
        try {
            String FORMATER = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            
            DateFormat format = new SimpleDateFormat(FORMATER);
            output =
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
    public void crearEnvio() {
        create(this.entity);
    }

    private static String tienditaWSDLOperation(int idCliente, int idProducto, int cantidad, int idTdc) {
        ws.TienditaWSDLService service = new ws.TienditaWSDLService();
        ws.TienditaWSDLPortType port = service.getTienditaWSDLPort();
        return port.tienditaWSDLOperation(idCliente, idProducto, cantidad, idTdc);
    }

    private static void create(wsenvios.Registro entity) {
        wsenvios.WSEnvios_Service service = new wsenvios.WSEnvios_Service();
        wsenvios.WSEnvios port = service.getWSEnviosPort();
        port.create(entity);
    }
    
    
}
