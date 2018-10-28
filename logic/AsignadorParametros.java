package logic;


import Actors.factories.dragons.Dragon;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class AsignadorParametros {

    private int[] arregloEdades;
    private ArrayList<Actors.factories.dragons.Dragon> Horde = new ArrayList<>();
    private Random random;
    private String[] arr = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    /**
     * Default constructor
     */
    public AsignadorParametros(){
        arregloEdades = new int[100];
        Horde = null;
        random = null;
    }

    /**
     * Constructor de acuerdo a la lista con elementos de tipo Dragon, provista
     * @param Horde
     */
    public AsignadorParametros(ArrayList<Dragon> Horde){
        arregloEdades = new int[Horde.size()];
        this.Horde = Horde;
        this.random = new Random();
    }

    //FUNCIONES PRINCIPALES

    public void asignaEdad(){

        generarArregloEdades();
        int cont = 0;

        for(Dragon dragon:Horde){
            dragon.setEdad(arregloEdades[cont]);
            cont++;
        }

    }

    public void asignaNombre(){
        String name;
        for(Dragon dragon:Horde){
            name = arr[random.nextInt(14)];
            dragon.setName(name + Integer.toString(random.nextInt(100)));
            System.out.println(dragon.getName());
        }
    }

    //FUNCIONES SECUNDARIAS
    /**
     * Imprime contenido en el array
     */
    public void printArray(){
        for (int i:arregloEdades){
            System.out.print(i + " ");

        }
    }

    /**
     * Genera un arreglo (de largo igual al largo de la lista) de edades aleatorias
     */
    public void generarArregloEdades(){

        int edad = 0;
        int cont = 0;
        int tope = Horde.size();

        while (cont < tope){
            edad = random.nextInt(1001);
            if (edad != 0){
                boolean verificador = false;
                for (int i = 0; i < cont+1; i++){
                    if (arregloEdades[i]==edad){
                        verificador = true;
                    }
                }

                if (verificador == false){
                    arregloEdades[cont] = edad;
                    cont++;
                }

            }
        }

    }




}
