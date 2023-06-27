/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO;

import controller.ed.lista.exception.EmptyException;
import controller.ed.lista.exception.PositionException;
import java.io.IOException;
import model.CuentaBanco;

/**
 *
 * @author cobos
 */
public class CuentaBancoDAO extends AdaptadorDAO<CuentaBanco>{
    
    private CuentaBanco cuenta;
    
    public CuentaBancoDAO() {
        super(CuentaBanco.class);
    }
       
    public CuentaBanco getCuenta() {
        if (this.cuenta == null) {
            this.cuenta = new CuentaBanco();
        }
        return cuenta;
    }

    public void setCuenta(CuentaBanco cuenta) {
        this.cuenta = cuenta;
    }
    
    public void modificar(Integer pos) throws EmptyException, PositionException, IOException {
        this.modificar(cuenta, pos); //To change body of generated methods, choose Tools | Templates.
    }

    public void guardar() throws IOException {
        this.guardar(cuenta); //To change body of generated methods, choose Tools | Templates.
    }
    
}
