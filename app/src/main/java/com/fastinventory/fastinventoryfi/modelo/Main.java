package com.fastinventory.fastinventoryfi.modelo;

public class Main {
    private String codProducto;
    private String nomProducto;
    private String preciProducto;
    private String cantProducto;
    private String fechProducto;

    public Main() {

    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getPreciProducto() {
        return preciProducto;
    }

    public void setPreciProducto(String preciProducto) {
        this.preciProducto = preciProducto;
    }

    public String getCantProducto() {return cantProducto;}

    public void setCantProducto(String cantProducto){this.cantProducto = cantProducto;}


    public String getFechProducto() {
        return fechProducto;
    }

    public void setFechProducto(String fechProducto) {
        this.fechProducto = fechProducto;
    }



    @Override
    public String toString() {
        return
                "codigo Producto :='" + codProducto + '\'' +
                ", nombre Producto :='" + nomProducto + '\'' +
                ", precio Producto :='" + preciProducto + '\'' +
                ", fecha :='" + fechProducto + '\'' +
                '}';
    }
}
