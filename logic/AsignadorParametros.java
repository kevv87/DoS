package logic;


import Actors.factories.dragons.Dragon;

import java.util.LinkedList;
import java.util.Random;

public class AsignadorParametros {

    public class NodoHijo {

        private int id;
        private int c_hijos;
        private NodoHijo siguiente;

        public NodoHijo(){
            id = 0;
            c_hijos = 2;
            siguiente = null;
        }

        public NodoHijo(int id){
            this.id = id;
            c_hijos = 2;
            siguiente = null;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getC_hijos() {
            return c_hijos;
        }

        public void setC_hijos(int c_hijos) {
            this.c_hijos = c_hijos;
        }

        public NodoHijo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoHijo siguiente) {
            this.siguiente = siguiente;
        }
    }

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

    public void asignaPadres(LinkedList<Dragon> listaRecorrido){

        LinkedList<NodoHijo> listaPosiblesPadres = new LinkedList<>();
        LinkedList<NodoHijo> listaRecorrido2 = new LinkedList<>();
        int cont = 0;

        for(Dragon dragon : listaRecorrido){
            dragon.setPadre(null);
            NodoHijo hijo = new NodoHijo(cont);
            listaPosiblesPadres.add(hijo);
            listaRecorrido2.add(hijo);
            cont++;
        }

        if (Horde.size() > 1){

            //DEFINIR AL PADRE (Omitir caso en el que se seleccione El mismo como padre)
            boolean bool = true;
            int idRandomFather = 0;
            while(bool){
                idRandomFather = random.nextInt(Horde.size());

                if (idRandomFather != 0){
                    bool=false;
                }
            }
            listaRecorrido2.remove(idRandomFather);
            listaPosiblesPadres.get(idRandomFather).setC_hijos(1);

            Dragon dragonHijo = Horde.get(0);
            dragonHijo.setPadre(Horde.get(idRandomFather));

            //listaRecorrido.remove(dragonHijo);
            listaPosiblesPadres.get(0).setC_hijos(1);
            listaRecorrido2.remove(0);
            //DEFINIR RELACIONES DE PADRES ALEATORIAMENTE

            for (NodoHijo dragonRecorrido : listaRecorrido2){

                bool = true;
                int idRandomNode = 0;

                while(bool){
                    idRandomNode = random.nextInt(listaPosiblesPadres.size());
                    if (dragonRecorrido.getId() != listaPosiblesPadres.get(idRandomNode).getId()){
                        if(Horde.get(listaPosiblesPadres.get(idRandomNode).getId()).getPadre()!=Horde.get(dragonRecorrido.getId())) {
                            bool = false;
                        }
                    }
                }

                NodoHijo limiteHijos = listaPosiblesPadres.get(idRandomNode);

                if (limiteHijos.getC_hijos() > 1){

                    Horde.get(dragonRecorrido.getId()).setPadre(Horde.get(limiteHijos.getId()));
                    limiteHijos.setC_hijos(limiteHijos.getC_hijos()-1);


                }

                else if(limiteHijos.getC_hijos() == 1){

                    Horde.get(dragonRecorrido.getId()).setPadre(Horde.get(limiteHijos.getId()));
                    listaPosiblesPadres.remove(limiteHijos);
                }
            }
        }
    }

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
        int velocidad = 0;
        int cont = 0;
        int tope = Horde.size();

        while (cont < tope){
            velocidad = random.nextInt(101);

            if (velocidad != 0){
                boolean verificador = false;
                for (int i = 0; i < cont+1; i++){
                    if (arregloVelocidades[i]==velocidad){
                        verificador = true;
                    }
                }

                if (verificador == false){
                    arregloVelocidades[cont] = velocidad;
                    cont++;
                }
            }
        }



    }



}