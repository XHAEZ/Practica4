/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.modeloTabla;

import controller.ed.lista.ListaEnlazada;
import controller.ed.lista.exception.EmptyException;
import controller.ed.lista.exception.PositionException;
import controller.util.Utilities;
import javax.swing.table.AbstractTableModel;
import model.CuentaBanco;

/**
 *
 * @author cobos
 */
public class ModeloTablaAleatoria extends AbstractTableModel{
    ListaEnlazada<CuentaBanco> datos = new ListaEnlazada<>();

    public ListaEnlazada<CuentaBanco> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<CuentaBanco> datos) {
        this.datos = datos;
    }
  
    @Override
    public int getRowCount() {
       return datos.size();
    }

    @Override
    public int getColumnCount() {
     return 2;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
       CuentaBanco c = null;
        try {
            c = datos.get(arg0);
        } catch (EmptyException | PositionException e) {
        }
       switch(arg1){
          case 0: return (c != null) ? c.getSaldo() : "No definido";
          case 1: return (c != null) ? c.getNombreTitular() : "No definido";
          default:
                return null;
       }
    }

      @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Saldo";
            case 1: return "Nombre Titular";
            default: return null;
        }
    }
    

}
