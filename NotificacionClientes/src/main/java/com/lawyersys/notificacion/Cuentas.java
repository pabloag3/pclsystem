package com.lawyersys.notificacion;

/**
 *
 * @author tatoa
 */
public class Cuentas {
    
    private int total;
    private int saldo;
    private String descripcion;
    private String correo;

    public Cuentas(int total, int saldo, String descripcion, String correo) {
        this.total = total;
        this.saldo = saldo;
        this.descripcion = descripcion;
        this.correo = correo;
    }

    public Cuentas() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cuentas{" + "total=" + total + ", saldo=" + saldo + ", descripcion=" + descripcion + ", correo=" + correo + '}';
    }
    
    
    
}
