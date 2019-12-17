/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstejbpagos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wservices.Tdc;

/**
 *
 * @author cecij
 */
public class TestEJB {

    private static final int TOTALSERVICES = 8;
    private static final int TOTALTDC = 16;
    private static final int TOTALCUSTOMERS = 16;
    private static final int MONTOMAX = 5000;
    private static final int LDCMAX = 7500;

    private static int getOperation() {
        return 1 + ((int) Math.round(Math.random() * (TOTALSERVICES - 1)));
    }

    private static int getTDCId() {
        return 1 + ((int) Math.round(Math.random() * (TOTALTDC - 1)));
    }

    private static double getMonto() {
        return 1 + ((double) Math.round(Math.random() * (MONTOMAX - 1)));
    }

    private static int getCustomerId() {
        return 1 + ((int) Math.round(Math.random() * (TOTALCUSTOMERS - 1)));
    }

    private static BigDecimal getLDC() {
        return  BigDecimal.valueOf(1 + ((int) Math.round(Math.random() * (LDCMAX - 1))));
    }

    public static String doRequest(wservices.WSPagosConTDC port) {
        
        String res = null;

        switch (getOperation()) {
            case 1:
                res = "total de pagos: " + count() + " pagos";
                break;

            case 2:
                try {
                    List<Tdc> tarjetas = findAll();
                    res = "Se encontro un total de " + tarjetas.size() + " tarjetas";
                } catch (Exception e) {
                    res = "Error al encontrar tarjetas";
                }
                break;

            case 3:
                res = "Se pudo realizar el pago: " + pagoConTDC(getTDCId(), getMonto());

                break;
            case 4:
                Tdc entity = new Tdc();
                entity.setCustomerId(getCustomerId());
                entity.setTdcId(getTDCId());
                entity.setLdc(getLDC());
                try {
                    create(entity);
                    res = "Se agregó una nueva tarjeta";
                } catch (Exception e) {
                    res = "No se pudo agregar nueva tarjeta";
                }
                break;

            case 5:
                Tdc editar = new Tdc();
                editar.setCustomerId(getCustomerId());
                editar.setTdcId(getTDCId());
                editar.setLdc(getLDC());
                try {
                    edit(editar);
                    res = "Se edito el registro de la tarjeta con exito";
                } catch (Exception e) {
                    res = "No se pudo editar el registro de la tarjeta";
                }
                break;
            case 6:
                try {
                    Tdc buscar = find(getTDCId());
                    res = "Se encontró la tarjeta: " + buscar.getTdcId();
                } catch (Exception e) {
                    res = "No se encontro la tarjeta";
                }
                break;
            case 7:
                 List<Integer> rango = new ArrayList<>();
                for (int i = 0; i < 15; i++) {
                    int id = getTDCId();
                    if (!rango.contains(id))
                        rango.add(id);
                }
                System.out.println("Elementos que se envian: " + rango.size());
                List<Tdc> r = findRange(rango);
                res =  "Del rango se encontraron: " + r.size() + " registros de " + rango.size();
                break;
            case 8:
                Tdc t = new Tdc();
                int id = getTDCId();
                t.setTdcId(id);
                try {
                    remove(t);
                    res = " Se elimino la tarjeta " + id;
                } catch (Exception e) {
                    res = "No se removio la tarjeta";
                }
                break;

            default:
                throw new AssertionError();
        }
        return res;
    }

    public static void main(String args[]) {
        
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        for (int i = 0; i < 10; i++) {
            System.out.println("Resultado de la operación: " + TestEJB.doRequest(port));
        }
    }

    private static java.util.List<wservices.Tdc> findAll() {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        return port.findAll();
    }

    private static int count() {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        return port.count();
    }

    private static boolean pagoConTDC(int intTDC, double dblMonto) {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        return port.pagoConTDC(intTDC, dblMonto);
    }

    private static void create(wservices.Tdc entity) {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        port.create(entity);
    }

    private static void edit(wservices.Tdc entity) {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        port.edit(entity);
    }

    private static Tdc find(int id) {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        return port.find(id);
    }

   

    private static void remove(wservices.Tdc entity) {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        port.remove(entity);
    }

    private static java.util.List<wservices.Tdc> findRange(java.util.List<java.lang.Integer> range) {
        wservices.WSPagos service = new wservices.WSPagos();
        wservices.WSPagosConTDC port = service.getWSPagosConTDCPort();
        return port.findRange(range);
    }

}
