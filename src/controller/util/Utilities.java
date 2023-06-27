/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import controller.ed.lista.ListaEnlazada;
import java.util.Random;

/**
 *
 * @author danny
 */
public class Utilities {
    public static void imprimir(Object [] objs){
        System.out.println("Lista de "+ objs.getClass().getSimpleName());
        for (Object o : objs) {
            System.out.println(o.toString());
        }
    }
    
   public static ListaEnlazada<Character> generateRandomData(int count) {
        ListaEnlazada<Character> data = new ListaEnlazada<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            data.insertar(randomChar);
        }

        return data;
    }
   
   
}
