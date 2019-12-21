package com.lawyersys.pclsystembe.dtos;

/**
 *
 * @author tatoa
 */
public class GastoPorFueroPorMesDTO {
    
    private int codFuero;
    private String tipoFuero;
    private int enero;
    private int febrero;
    private int marzo;
    private int abril;
    private int mayo;
    private int junio;
    private int julio;
    private int agosto;
    private int septiembre;
    private int octubre;
    private int noviembre;
    private int diciembre;

    public GastoPorFueroPorMesDTO() {
    }

    public GastoPorFueroPorMesDTO(int codFuero, String tipoFuero, int enero, int febrero, int marzo, int abril, int mayo, int junio, int julio, int agosto, int septiembre, int octubre, int noviembre, int diciembre) {
        this.codFuero = codFuero;
        this.tipoFuero = tipoFuero;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.abril = abril;
        this.mayo = mayo;
        this.junio = junio;
        this.julio = julio;
        this.agosto = agosto;
        this.septiembre = septiembre;
        this.octubre = octubre;
        this.noviembre = noviembre;
        this.diciembre = diciembre;
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

    public int getEnero() {
        return enero;
    }

    public void setEnero(int enero) {
        this.enero = enero;
    }

    public int getFebrero() {
        return febrero;
    }

    public void setFebrero(int febrero) {
        this.febrero = febrero;
    }

    public int getMarzo() {
        return marzo;
    }

    public void setMarzo(int marzo) {
        this.marzo = marzo;
    }

    public int getAbril() {
        return abril;
    }

    public void setAbril(int abril) {
        this.abril = abril;
    }

    public int getMayo() {
        return mayo;
    }

    public void setMayo(int mayo) {
        this.mayo = mayo;
    }

    public int getJunio() {
        return junio;
    }

    public void setJunio(int junio) {
        this.junio = junio;
    }

    public int getJulio() {
        return julio;
    }

    public void setJulio(int julio) {
        this.julio = julio;
    }

    public int getAgosto() {
        return agosto;
    }

    public void setAgosto(int agosto) {
        this.agosto = agosto;
    }

    public int getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(int septiembre) {
        this.septiembre = septiembre;
    }

    public int getOctubre() {
        return octubre;
    }

    public void setOctubre(int octubre) {
        this.octubre = octubre;
    }

    public int getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(int noviembre) {
        this.noviembre = noviembre;
    }

    public int getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(int diciembre) {
        this.diciembre = diciembre;
    }

    @Override
    public String toString() {
        return "GastoPorFueroPorMesDTO{" + "codFuero=" + codFuero + ", tipoFuero=" + tipoFuero + ", enero=" + enero + ", febrero=" + febrero + ", marzo=" + marzo + ", abril=" + abril + ", mayo=" + mayo + ", junio=" + junio + ", julio=" + julio + ", agosto=" + agosto + ", septiembre=" + septiembre + ", octubre=" + octubre + ", noviembre=" + noviembre + ", diciembre=" + diciembre + '}';
    }
    
    
    
}
