package com.lawyersys.pclsystembe.dtos;

/**
 *
 * @author tatoa
 */
public class TiempoDeServiciosPorFueroDTO {
    
    private int codFuero;
    private String tipoFuero;
    private int meses;

    public TiempoDeServiciosPorFueroDTO() {
    }

    public TiempoDeServiciosPorFueroDTO(int codFuero, String tipoFuero, int meses) {
        this.codFuero = codFuero;
        this.tipoFuero = tipoFuero;
        this.meses = meses;
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

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    @Override
    public String toString() {
        return "TiempoDeServiciosPorFueroDTO{" + "codFuero=" + codFuero + ", tipoFuero=" + tipoFuero + ", meses=" + meses + '}';
    }
    
}
