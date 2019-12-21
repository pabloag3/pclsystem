package com.lawyersys.pclsystembe.dtos;

import com.lawyersys.pclsystembacke.entities.Pagos;
import java.util.ArrayList;

/**
 *
 * @author tatoa
 */
public class ListaPagosDTO {
    
    private ArrayList<Pagos> listaPagos;

    public ListaPagosDTO() {
    }

    public ListaPagosDTO(ArrayList<Pagos> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public ArrayList<Pagos> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(ArrayList<Pagos> listaPagos) {
        this.listaPagos = listaPagos;
    }

    @Override
    public String toString() {
        return "ListaPagosDTO{" + "listaPagos=" + listaPagos + '}';
    }
    
}
