/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DAO;

import controller.ed.lista.ListaEnlazada;
import controller.ed.lista.exception.EmptyException;
import controller.ed.lista.exception.PositionException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 *
 * @author alyce
 */
public class AdaptadorDAO <T> implements InterfazDAO<T> {
    private Conexion conexion;
    private Class clazz;
    private String url;
    public static Integer ASCENDENTE = 0;
    public static Integer DESCENDENTE = 1;

    public AdaptadorDAO(Class clazz) {
        this.conexion = new Conexion();
        this.clazz = clazz;
        this.url = Conexion.URL+clazz.getSimpleName().toLowerCase()+".json";
    }
    
    @Override
    public void guardar(T obj) throws IOException {
       ListaEnlazada<T> lista = listar();
       lista.insertar(obj);
        conexion.getXstream().alias(lista.getClass().getName(),ListaEnlazada.class);
        conexion.getXstream().toXML(lista, new FileWriter(url));
    }

    @Override
    public void modificar(T obj, Integer pos) throws EmptyException, PositionException, IOException{
       ListaEnlazada<T> lista = listar();
       lista.update(pos,obj);
       //lista.update(obj, pos);
       conexion.getXstream().alias(lista.getClass().getName(), ListaEnlazada.class);
       conexion.getXstream().toXML(lista, new FileWriter(url));
    }

    @Override
    public ListaEnlazada<T> listar(){
        ListaEnlazada<T> lista = new ListaEnlazada<>();
        try {
        //ListaEnlazada<T> lista = (ListaEnlazada<T>)conexion.getXstream().fromXML(new FileReader(url));
        lista = (ListaEnlazada<T>)conexion.getXstream().fromXML(new File(url));
        } catch (Exception e) {
             System.out.println(e);
        }
        return lista;
    }

    @Override
    public T obtener(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Integer generarId(){
        return listar().size()+1;
    }
    
}