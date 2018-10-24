package logic;

import Actors.factories.DragonFactory;
import Actors.factories.dragons.Dragon;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Sorter {



        public static LinkedList<Dragon> quickSortAscendente(LinkedList<Dragon> listaDragones){

            int tamanoListaDragones = listaDragones.size();
            int[] arregloEdades = new int[tamanoListaDragones];
            int cont = 0;
            LinkedList<Dragon> nuevaLista = new LinkedList<>();

            if (tamanoListaDragones > 0){
                for (Dragon dragon: listaDragones){
                    arregloEdades[cont] = dragon.getEdad();
                    cont++;
                }
                //Ordenamiento por QuickSort
                quicksort(arregloEdades,0, tamanoListaDragones-1);

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


        //FUNCIONES SECUNDARIAS

        /** Funcion que ejecuta Quicksort
         * @param arr Arreglo de datos
         * @param low numero menor a la izquierda del pivote
         * @param high numero mayor a la derecha del pivote
         */
        public static void quicksort(int arr[], int low, int high)
        {
            if (low < high)
            {
            /* pi is partitioning index, arr[pi] is
              now at right place */
                int pi = partition(arr, low, high);

                // Recursively sort elements before
                // partition and after partition
                quicksort(arr, low, pi-1);
                quicksort(arr, pi+1, high);
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
        private static int partition(int arr[], int low, int high)
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
}
