package com.company;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Consola {


    public static void inputs(){

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Como desea introducir los datos:\n" +
                "1. Uno por uno\n" +
                "2. Mediante la lista prehecha (camino rapido y mejor)");

        String intro = myObj.nextLine();
        if (Objects.equals(intro, "1")){
        System.out.println("Ingrese la cantidad de equipos: ");
        String cantidad_de_equipos = myObj.nextLine();  // Read user input

        System.out.println("Ingrese la cantidad de partidos jugados: ");
        String partidos_jugados = myObj.nextLine();
        String[][] equipos = nombres_de_equipos(Integer.parseInt(cantidad_de_equipos));// equipos[i][j] tienen nombre [j = 0] y puntaje [j = 1]
        String[][] partidos = partidos(Integer.parseInt(partidos_jugados), equipos);
        presentar_data(equipos, partidos);
        solucion(equipos, partidos);}
        else if(Objects.equals(intro, "2")){
            String[][] equipos = new String[][]{{"River", "4"}, {"Racing", "6"}, {"Boca", "3"},
                    {"Independiente", "5"}, {"Estudiantes", "10"}, {"Gimnasia", "0"}};
            String[][] partidos = partidos_random(8, equipos);
            presentar_data(equipos, partidos);
            solucion(equipos, partidos);
        }
    }

    static String[][] nombres_de_equipos(int cantidad_de_equipos) { // No pueden haber equipos repetidos
        String[][] equipos = new String[cantidad_de_equipos][2];
        Scanner myObj = new Scanner(System.in);
        for (int i = 0; i < cantidad_de_equipos; i++) {
            boolean equipo_repetido = true;
            while (equipo_repetido) {
                System.out.println("Ingrese nombre del equipo: ");
                String nombre = myObj.nextLine();
                equipo_repetido = false;
                for (int j = 0; j < cantidad_de_equipos; j++) {
                    if (Objects.equals(equipos[j][0], nombre)) {
                        equipo_repetido = true;
                        System.out.println("Equipo Repetido");
                        break;
                    }
                }
                if (!equipo_repetido) {
                    System.out.println("Ingrese la cantidad de puntos: ");
                    String puntos = myObj.nextLine();
                    equipos[i][0] = nombre;
                    equipos[i][1] = puntos;
                    equipo_repetido = false;
                }


            }
        }
        return equipos;
    }

    static String[][] partidos(int cantidad_de_partidos, String[][] equipos) { //podrían haber partidos repetidos.
        String[][] partidos = new String[cantidad_de_partidos][2];
        Scanner myObj = new Scanner(System.in);
        String equipo_local = null;
        for (int i = 0; i < cantidad_de_partidos; i++) {
            boolean equipo_existe = false;
            System.out.println("Partido " + Integer.toString(i + 1));
            while (!equipo_existe) {
                System.out.println("Ingrese el local: ");
                String local = myObj.nextLine();
                equipo_existe = existe_el_equipo(local, equipos);
                if (equipo_existe){
                    partidos[i][0] = local;
                    equipo_local = local;
                }
                else{System.out.println("Equipo inexistente.");}
            }

            equipo_existe = false;
            while (!equipo_existe){
                System.out.println("Ingrese el visitante: ");
                String visitante = myObj.nextLine();
                equipo_existe = existe_el_equipo(visitante, equipos);
                if (equipo_existe && !Objects.equals(equipo_local, visitante)){
                    partidos[i][1] = visitante;
                }
                else{equipo_existe = false;
                    System.out.println("Equipo inexistente o igual al local");}
            }

            }
        return partidos;
        }

    static boolean existe_el_equipo(String equipo, String[][] equipos){
        for (String[] strings : equipos) { // por cada elemento en equipos
            if (Objects.equals(strings[0], equipo)) {
                return true;
            }
        }
        return false;
    }

    static void solucion(String[][] equipos, String[][] partidos){
        System.out.println("""
                \n¿Con que algoritmo desea resolver el programa?
                1. Brute Force
                2. Backtracking
                3. Divide and Conquer
                0. Salir""");
        Scanner myObj = new Scanner(System.in);
        String numero = myObj.nextLine();
        if (Objects.equals(numero, "1"));
        else if (Objects.equals(numero, "2"));
        else if (Objects.equals(numero, "3"));
        else if (Objects.equals(numero, "0")){
            System.out.println("Saliendo del programa...");}
        else{
            System.out.println("Input no válido");
            solucion(equipos, partidos);}
    }


    static String[][] partidos_random(int cantidad_de_partidos, String[][] equipos){
        int min = 0;
        int max = equipos.length;

        String[][] partidos = new String[cantidad_de_partidos][2];
        for (int i = 0; i < cantidad_de_partidos; i++){
            Random random = new Random();
            int nro1 = random.nextInt(min + max);
            int nro2 = random.nextInt(min + max);
            while(nro1 == nro2){
                nro2 = random.nextInt(min + max);}
            partidos[i][0] = equipos[nro1][0];
            partidos[i][1] = equipos[nro2][0];
        }
        return partidos;
    }
    static void presentar_data(String[][] equipos, String[][] partidos){
        System.out.println(equipos.length + " " + partidos.length);
        for (int i = 0; i < equipos.length; i++){
            System.out.println(equipos[i][0] + " " + equipos[i][1]);
        }
        System.out.println("");
        for (String[] partido : partidos) {
            System.out.println(partido[0] + " " + partido[1]);
        }
    }
}
