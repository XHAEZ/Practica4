/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.ed.lista.ListaEnlazada;
import java.util.Random;
import model.CuentaBanco;

/**
 *
 * @author cobos
 */
public class ControladorCuenta {

    ListaEnlazada<CuentaBanco> cuenta;
    CuentaBanco info;

    public ControladorCuenta() {
        cuenta = new ListaEnlazada<>();
        Random rand = new Random();
        for (int i = 1; i <= 20000; i++) {
            info = new CuentaBanco();
            info.setSaldo(Math.round((90.99 + (90.99 - (-90.99)) * rand.nextDouble()) * 100.0) / 100.0);
            info.setNombreTitular(generarNombreRandom());
            cuenta.insertar(info);
        }
    }

    private String generarNombreRandom() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int nameLength = random.nextInt(6) + 5; // Genera nombres de longitud entre 5 y 10 caracteres

        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < nameLength; i++) {
            int randomIndex = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(randomIndex);
            nameBuilder.append(randomChar);
        }

        String randomName = nameBuilder.toString();
        return randomName;
    }

    public ListaEnlazada<CuentaBanco> getCuenta() {
        if (cuenta == null) {
            cuenta = new ListaEnlazada<>();
        }
        return cuenta;
    }

    public void setCuenta(ListaEnlazada<CuentaBanco> cuenta) {
        this.cuenta = cuenta;
    }

    /**
     *
     * @param cuenta
     * @param tipoOrden
     * @return
     */
    public ListaEnlazada<CuentaBanco> mergeSort(ListaEnlazada<CuentaBanco> cuenta, Integer tipoOrden, Integer atributoOrden) {
        // Comprueba si la lista está vacía o solo contiene un elemento, en cuyo caso ya está ordenada
        if (cuenta == null || cuenta.size() <= 1) {
            return cuenta;
        }
        // Convierte la lista en un arreglo para facilitar el ordenamiento
        CuentaBanco[] datos = cuenta.toArray();
        // Llama al método mergeSort para ordenar el arreglo
        mergeSort(datos, tipoOrden, atributoOrden);
        // Convierte el arreglo ordenado nuevamente en una lista enlazada
        return cuenta.toList(datos);
    }

    /**
     *
     * @param arreglo
     * @param tipoOrden
     */
    private static void mergeSort(CuentaBanco[] arreglo, int tipoOrden, Integer atributoOrden) {
        // Verifica si hay elementos para ordenar
        if (arreglo.length <= 1) {
            return;
        }
        // Divide el arreglo en dos mitades
        int mid = arreglo.length / 2;
        CuentaBanco[] arregloIzquierda = new CuentaBanco[mid];
        CuentaBanco[] arregloDerecha = new CuentaBanco[arreglo.length - mid];
        // Copia los elementos correspondientes a cada mitad en arreglos separados
        System.arraycopy(arreglo, 0, arregloIzquierda, 0, mid);
        System.arraycopy(arreglo, mid, arregloDerecha, 0, arreglo.length - mid);
        // Aplica mergeSort recursivamente a las dos mitades
        mergeSort(arregloIzquierda, tipoOrden, atributoOrden);
        mergeSort(arregloDerecha, tipoOrden, atributoOrden);
        // Combina las dos mitades ordenadas mediante el método merge
        merge(arreglo, arregloIzquierda, arregloDerecha, tipoOrden, atributoOrden);
    }

    /**
     *
     * @param arreglo
     * @param arregloIzquierda
     * @param arregloDerecha
     * @param tipoOrden
     */
    private static void merge(CuentaBanco[] arreglo, CuentaBanco[] arregloIzquierda, CuentaBanco[] arregloDerecha, int tipoOrden, Integer atributoOrden) {
        int leftSize = arregloIzquierda.length;
        int rightSize = arregloDerecha.length;
        int indiceIzquierda = 0;
        int indiceDerecha = 0;
        int indiceArreglo = 0;

        switch (atributoOrden) {
            case 0:
                // Combinación de las dos mitades en orden
                while (indiceIzquierda < leftSize && indiceDerecha < rightSize) {
                    if (tipoOrden == 0) { // Orden ascendente
                        if (arregloIzquierda[indiceIzquierda].getSaldo() <= arregloDerecha[indiceDerecha].getSaldo()) {
                            arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
                            indiceIzquierda++;
                        } else {
                            arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
                            indiceDerecha++;
                        }
                    } else if (tipoOrden == 1) { // Orden descendente
                        if (arregloIzquierda[indiceIzquierda].getSaldo() >= arregloDerecha[indiceDerecha].getSaldo()) {
                            arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
                            indiceIzquierda++;
                        } else {
                            arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
                            indiceDerecha++;
                        }
                    }
                    indiceArreglo++;
                }
            case 1:
               while (indiceIzquierda < leftSize && indiceDerecha < rightSize) {
                    if (tipoOrden == 0) { // Orden ascendente
                        if (arregloIzquierda[indiceIzquierda].getNombreTitular().compareTo(arregloDerecha[indiceDerecha].getNombreTitular()) < 0) {
                            arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
                            indiceIzquierda++;
                        } else {
                            arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
                            indiceDerecha++;
                        }
                    } else if (tipoOrden == 1) { // Orden descendente
                    if (arregloIzquierda[indiceIzquierda].getNombreTitular().compareTo(arregloDerecha[indiceDerecha].getNombreTitular()) > 0) {
                        arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
                        indiceIzquierda++;
                    } else {
                        arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
                        indiceDerecha++;
                    }
                }
                indiceArreglo++;
                }
            default:
        }

        // Copia los elementos restantes de la mitad izquierda, si los hay
        while (indiceIzquierda < leftSize) {
            arreglo[indiceArreglo] = arregloIzquierda[indiceIzquierda];
            indiceIzquierda++;
            indiceArreglo++;
        }

        // Copia los elementos restantes de la mitad derecha, si los hay
        while (indiceDerecha < rightSize) {
            arreglo[indiceArreglo] = arregloDerecha[indiceDerecha];
            indiceDerecha++;
            indiceArreglo++;
        }
    }

        /**
     *
     * @param lista
     * @param tipoOrden
     * @return
     */
    public ListaEnlazada<CuentaBanco> quickSort(ListaEnlazada<CuentaBanco> lista, Integer tipoOrden, Integer atributoOrden) {
        // Comprueba si la lista está vacía o solo contiene un elemento, en cuyo caso ya está ordenada
        if (lista == null || lista.size() <= 1) {
            return lista;
        }
        // Convierte la lista en un arreglo para facilitar el ordenamiento
        CuentaBanco[] arreglo = lista.toArray();
        // Llama al método quickSort para ordenar el arreglo
        quickSort(arreglo, 0, arreglo.length - 1, tipoOrden, atributoOrden);
        // Convierte el arreglo ordenado nuevamente en una lista enlazada
        return lista.toList(arreglo);
    }

    /**
     *
     * @param arreglo
     * @param bajo
     * @param alto
     * @param tipoOrden
     */
    private static void quickSort(CuentaBanco[] arreglo, int bajo, int alto, int tipoOrden, Integer atributoOrden) {
        // Verifica si hay elementos para ordenar
        if (bajo < alto) {
            // Divide el arreglo y obtiene el índice de la división
            int indiceDivision = division(arreglo, bajo, alto, tipoOrden, atributoOrden);
            // Ordena recursivamente la mitad inferior antes de la división
            quickSort(arreglo, bajo, indiceDivision - 1, tipoOrden, atributoOrden);
            // Ordena recursivamente la mitad superior después de la división
            quickSort(arreglo, indiceDivision + 1, alto, tipoOrden, atributoOrden);
        }
    }

    /**
     *
     * @param arreglo
     * @param bajo
     * @param alto
     * @param tipoOrden
     * @return
     */
    private static int division(CuentaBanco[] arreglo, int bajo, int alto, int tipoOrden, Integer atributoOrden) {
        // Selecciona el pivote como el último elemento del arreglo
        CuentaBanco pivote = arreglo[alto];
        int i = bajo - 1;
        switch (atributoOrden) {
            case 0:
                // Itera sobre el arreglo desde el índice bajo hasta el índice alto - 1
                for (int j = bajo; j < alto; j++) {
                    // Comprueba el tipo de ordenamiento y realiza el intercambio si es necesario
                    if (tipoOrden == 0) {
                        if (arreglo[j].getSaldo() < pivote.getSaldo()) {
                            i++;
                            intercambio(arreglo, i, j);
                        }
                    } else {
                        if (arreglo[j].getSaldo() > pivote.getSaldo()) {
                            i++;
                            intercambio(arreglo, i, j);
                        }
                    }
                }
                // Intercambia el pivote con el elemento en la posición i + 1
                intercambio(arreglo, i + 1, alto);
                // Devuelve la posición del pivote después de la división
                return i + 1;
            case 1:
                // Itera sobre el arreglo desde el índice bajo hasta el índice alto - 1
                for (int j = bajo; j < alto; j++) {
                    // Comprueba el tipo de ordenamiento y realiza el intercambio si es necesario
                    if (tipoOrden == 0) { // Orden ascendente
                        if (arreglo[j].getNombreTitular().compareTo(pivote.getNombreTitular()) < 0) {
                            i++;
                            intercambio(arreglo, i, j);
                        }
                    } else { // Orden descendente
                        if (arreglo[j].getNombreTitular().compareTo(pivote.getNombreTitular()) > 0) {
                            i++;
                            intercambio(arreglo, i, j);
                        }
                    }
                }
        }
        // Intercambia el pivote con el elemento en la posición i + 1
        intercambio(arreglo, i + 1, alto);
        // Devuelve la posición del pivote después de la división
        return i + 1;
    }

    /**
     *
     * @param arreglo
     * @param i
     * @param j
     */
    private static void intercambio(CuentaBanco[] arreglo, int i, int j) {
        // Intercambia los elementos en las posiciones i y j del arreglo
        CuentaBanco temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
    
}
