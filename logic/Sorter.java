package logic;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import utils.ArbolBinario;
import utils.NodoArbol;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Sorter {

    private static Random random = new Random();

    public static LinkedList<Dragon> arbolBinario(LinkedList<Dragon> listaDragones){
        //LIST TO TREE both parameters
        Queue<Dragon> colaDragones = new LinkedList<>();
        ArbolBinario arbolBinario = new ArbolBinario();
        Dragon padre = DragonFactory.getDragon("A",0,0,"A1",null);


        //primer caso:
        for (Dragon dragon : listaDragones){
            if (dragon.getPadre() == null){
                padre = dragon;
                break;
            }
        }
        //listaDragones.remove(padre);
        colaDragones.add(padre);
        arbolBinario.insert(null,padre);
        while (colaDragones.isEmpty()==false){

            for (Dragon dragon: listaDragones){

                if (dragon != padre) { //REVISAR

                    if (dragon.getPadre() == ((LinkedList<Dragon>) colaDragones).getFirst()) {
                        //localiza en el arbol al padre, e inserta el hijo
                        NodoArbol nodoPadre = arbolBinario.iterativeSearch(((LinkedList<Dragon>) colaDragones).getFirst());
                        arbolBinario.insert(nodoPadre, dragon);
                        colaDragones.add(dragon);
                    }
                }
            }
            ((LinkedList<Dragon>) colaDragones).pop();
        }

        //SET POSITIONS
        arbolBinario.setPositions(arbolBinario.getRaiz(),0,350, listaDragones.getFirst());

        //TREE TO LIST

        arbolBinario.preorderToList(arbolBinario.getRaiz());
        LinkedList<Dragon> nuevaLista =arbolBinario.getListaPreorden();

        return nuevaLista;

    }

    public static LinkedList<Dragon> AVLTree (LinkedList<Dragon> listaDragones){

        //LIST TO TREE

        AVLTree avlTree = new AVLTree(listaDragones);

        //SET POSITIONS
        avlTree.setPositions(avlTree.getRoot(),0,350,listaDragones.getFirst());

        //TREE TO LIST

        avlTree.arrangedPos(avlTree.getRoot());
        LinkedList<Dragon> nuevaLista = avlTree.getList();

        return nuevaLista;
    }


    public static LinkedList<Dragon> quickSort(LinkedList<Dragon> listaDragones){

        int aleatorio = random.nextInt(10);

        int tamanoListaDragones = listaDragones.size();
        int[] arregloEdades = new int[tamanoListaDragones];
        int cont = 0;
        LinkedList<Dragon> nuevaLista = new LinkedList<>();

        if (tamanoListaDragones > 0){
            for (Dragon dragon: listaDragones){
                arregloEdades[cont] = dragon.getEdad();
                cont++;
            }

            //Ordenamiento Aleatorio por QuickSort

            if (aleatorio % 2 == 0){
                quicksortMenorMayor(arregloEdades,0, tamanoListaDragones-1);
                System.out.println("QuickSort: Menor a mayor");
            }

            else if (aleatorio % 2 == 1){
                quicksortMayorMenor(arregloEdades,0, tamanoListaDragones-1);
                System.out.println("QuickSort: Mayor a menor");
            }


            //Nueva lista ordenada
            cont = 0;
            while (cont < tamanoListaDragones){
                Dragon dragon1 = DragonFactory.getDragon("A",0, 0, "probe", null);
                for (Dragon dragon : listaDragones) {
                    if (dragon.getEdad()==arregloEdades[cont]){
                        nuevaLista.add(dragon);
                        break;
                    }
                }
                cont++;
            }

        }

        return nuevaLista;

    }


    /**
     *
     * @param listaDragones
     * @return linkedList con dragones ordenados por edad mediante SelectionSort
     */
    public static LinkedList<Dragon> selectionSort(LinkedList<Dragon> listaDragones){

        int aleatorio = random.nextInt(10);

        int tamanoListaDragones = listaDragones.size();
        int[] arregloEdades = new int[tamanoListaDragones];
        int cont = 0;
        LinkedList<Dragon> nuevaLista = new LinkedList<>();

        if (tamanoListaDragones > 0){
            for (Dragon dragon: listaDragones){
                arregloEdades[cont] = dragon.getEdad();
                cont++;
            }

            //Ordenamiento Aleatorio por SelectionSort

            if (aleatorio % 2 == 0){
                selectionSortAscendente(arregloEdades);
                System.out.println("Selection: Menor a mayor");
            }

            else if (aleatorio % 2 == 1){
                selectionSortDescendiente(arregloEdades);
                System.out.println("Selection: Mayor a menor");
            }


            //Nueva lista ordenada
            cont = 0;
            while (cont < tamanoListaDragones){
                for (Dragon dragon : listaDragones) {
                    if (dragon.getEdad()==arregloEdades[cont]){
                        nuevaLista.add(dragon);
                        break;
                    }
                }
                cont++;
            }

        }

        return nuevaLista;

    }


    /**
     *
     * @param listaDragones
     * @return linkedList con dragones ordenados por edad mediante SelectionSort
     */
    public static LinkedList<Dragon> insertionSort(LinkedList<Dragon> listaDragones){

        int aleatorio = random.nextInt(10);

        int tamanoListaDragones = listaDragones.size();
        int[] arregloVelocidades = new int[tamanoListaDragones];
        int cont = 0;
        LinkedList<Dragon> nuevaLista = new LinkedList<>();

        if (tamanoListaDragones > 0){
            for (Dragon dragon: listaDragones){
                arregloVelocidades[cont] = dragon.getVelocidad_recarga();
                cont++;
            }

            //Ordenamiento Aleatorio por insertionSort

            if (aleatorio % 2 == 0){
                insertionSortAscendente(arregloVelocidades);
                System.out.println("Insertion: Menor a mayor");
            }

            else if (aleatorio % 2 == 1){
                insertionSortDescendente(arregloVelocidades);
                System.out.println("Insertion: Mayor a menor");
            }


            //Nueva lista ordenada
            cont = 0;
            while (cont < tamanoListaDragones){
                for (Dragon dragon : listaDragones) {
                    if (dragon.getVelocidad_recarga()==arregloVelocidades[cont]){
                        nuevaLista.add(dragon);
                        break;
                    }
                }
                cont++;
            }
        }



        return nuevaLista;

    }




    //FUNCIONES SECUNDARIAS

    /** Funcion que ejecuta Quicksort
     * @param arr Arreglo de datos
     * @param low numero menor a la izquierda del pivote
     * @param high numero mayor a la derecha del pivote
     */
    private static void quicksortMenorMayor(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partitionMenorMayor(arr, low, high);

            // Recursively sort elements before
            // partitionMenorMayor and after partitionMenorMayor
            quicksortMenorMayor(arr, low, pi-1);
            quicksortMenorMayor(arr, pi+1, high);
        }

    }

    /** Toma como ultimo elemento como pivote,
     lo coloca en la posicion correcta de una
     manera ordenada, y coloca todos los numeros
     menores que el pivote a la izquierda y los
     mayores a la derecha
     * @param arr Arreglo por ordenar
     * @param low index del inicio
     * @param high index del final
     */
    private static int partitionMenorMayor(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    /** Funcion que ejecuta Quicksort
     * @param arr Arreglo de datos
     * @param low numero menor a la izquierda del pivote
     * @param high numero mayor a la derecha del pivote
     */
    private static void quicksortMayorMenor(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partitionMayorMenor(arr, low, high);

            // Recursively sort elements before
            // partitionMenorMayor and after partitionMenorMayor
            quicksortMayorMenor(arr, low, pi-1);
            quicksortMayorMenor(arr, pi+1, high);
        }

    }

    //FUNCIONES SECUNDARIAS
    /** Toma como ultimo elemento como pivote,
     lo coloca en la posicion correcta de una
     manera ordenada, y coloca todos los numeros
     menores que el pivote a la izquierda y los
     mayores a la derecha
     * @param arr Arreglo por ordenar
     * @param low index del inicio
     * @param high index del final
     */
    private static int partitionMayorMenor(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] >= pivot)
            {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    //////////SELECTION SORT

    private static void selectionSortAscendente(int arr[])
    {
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    private static void selectionSortDescendiente(int arr[])
    {
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the maximum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] > arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }


    public static void insertionSortAscendente(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }

    public static void insertionSortDescendente(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] < key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }




}