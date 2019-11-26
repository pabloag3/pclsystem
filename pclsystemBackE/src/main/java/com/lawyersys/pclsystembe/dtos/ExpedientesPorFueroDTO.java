package com.lawyersys.pclsystembe.dtos;

/**
 *
 * @author tatoa
 */
public class ExpedientesPorFueroDTO {
    
    private int codFuero;
    private String tipoFuero;
    private int cantExpedientes;

    public ExpedientesPorFueroDTO() {
    }

    public ExpedientesPorFueroDTO(int codFuero, String tipoFuero, int cantExpedientes) {
        this.codFuero = codFuero;
        this.tipoFuero = tipoFuero;
        this.cantExpedientes = cantExpedientes;
    }

    public int getCodFuero() {
        return codFuero;
    }

    public void setCodFuero(int codFuero) {
        this.codFuero = codFuero;
    }

    public String getTipoFuero() {
        return tipoFuero;
    }

    public void setTipoFuero(String tipoFuero) {
        this.tipoFuero = tipoFuero;
    }

    public int getCantExpedientes() {
        return cantExpedientes;
    }

    public void setCantExpedientes(int cantExpedientes) {
        this.cantExpedientes = cantExpedientes;
    }

    @Override
    public String toString() {
        return "ExpedientesPorFuero{" + "codFuero=" + codFuero + ", tipoFuero=" + tipoFuero + ", cantExpedientes=" + cantExpedientes + '}';
    }
    
}
