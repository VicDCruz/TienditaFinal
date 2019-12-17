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
    private int cId;
    private int eId;
    private XMLGregorianCalendar sDate;
    private int oId;
    private int status;

    public Envio(Date date, int customerId, int entregaId, int ordenId) {
        XMLGregorianCalendar shippingDate = this.getXMLDate(date);
        
        this.cId = customerId;
        this.eId = entregaId;
        this.sDate = shippingDate;
        this.oId = ordenId;
        this.status = 1;
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
        ws.Registro r = new ws.Registro();
        r.setCustomerId(this.cId);
        r.setEntregaId(this.eId);
        r.setFechaEntregado(this.sDate);
        r.setFechaPrometida(this.sDate);
        r.setOrdenCompra(this.oId);
        r.setStatus(this.status);
        create(r);
    }

    private static void create(ws.Registro entity) {
        ws.WSEnvios_Service service = new ws.WSEnvios_Service();
        ws.WSEnvios port = service.getWSEnviosPort();
        port.create(entity);
    }
}
