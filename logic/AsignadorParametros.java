package logic;


import Actors.factories.dragons.Dragon;
import java.util.LinkedList;
import java.util.Random;

public class AsignadorParametros {

    private int[] arregloEdades;
    private int[] arregloVelocidades;
    private LinkedList<Dragon> Horde = new LinkedList<>();
    private Random random;

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
    public AsignadorParametros(LinkedList<Dragon> Horde){
        arregloEdades = new int[Horde.size()];
        arregloVelocidades = new int[Horde.size()];
        this.Horde = Horde;
        this.random = new Random();
    }

    //FUNCIONES PRINCIPALES

    public void asignaEdad(){

        generarArregloEdades();
        int cont = 0;

        for(Dragon dragon: Horde){
            dragon.setEdad(arregloEdades[cont]);
            cont++;
        }

    }

    public void asignaVelocidad(){

        generarArregloVelocidades();
        int cont = 0;

        for (Dragon dragon: Horde){
            dragon.setVelocidad_recarga(arregloVelocidades[cont]);
            cont++;
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

    public void generarArregloVelocidades(){
        int cont = 0;
        for(Dragon dragon: Horde){
            if (dragon.getTipo().equals("A")){
                int velocidad = random.nextInt(21);
                while (velocidad == 0) {
                    velocidad = random.nextInt(21);
                }

                arregloVelocidades[cont]=velocidad;
                cont++;
            }

            else if (dragon.getTipo().equals("B")){
                int velocidad = random.nextInt(61);
                while (velocidad == 0 || velocidad < 21) {
                    velocidad = random.nextInt(61);
                }
                arregloVelocidades[cont]=velocidad;
                cont++;
            }

            else if ((dragon.getTipo().equals("C"))){
                int velocidad = random.nextInt(101);
                while (velocidad == 0 || velocidad < 61) {
                    velocidad = random.nextInt(101);
                }
                arregloVelocidades[cont]=velocidad;
                cont++;
            }
        }



    }



}
