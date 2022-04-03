import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static String UNDERLINE = "\033[4m";
    static String BOLD = "\033[1m";
    static String END = "\033[0m";

    public static void main(String[] args) {
        Main metodos = new Main();
        System.out.println("\t\t\t\t\t\t\t" + BOLD + UNDERLINE + "PROGRAMA CAMBIAVOCALES" + END);
        System.out.println("");
        System.out.println("");
        metodos.Escribir();
        metodos.Leer();
    }
    public void Escribir(){
        Scanner pt = new Scanner(System.in);
        PrintWriter salida = null;
        try {
            salida = new PrintWriter("src/mitexto.txt");
            String cadena;
            System.out.println(BOLD + "Introduzca el texto:" + END);
            int cont = 0;
            System.out.print("\t");
            cadena = pt.nextLine();
            do {
                while (cadena.length() > 80) {
                    System.out.println("Ha excedido los 80 caracteres");
                    System.out.println("Introduzca una nueva cadena en su lugar:");
                    System.out.print("\t");
                    cadena = pt.nextLine();
                }
                salida.println(cadena);
                System.out.print("\t");
                if(cont < 5) {
                    cadena = pt.nextLine();
                }
                cont++;
            } while (cont < 6 && !cadena.equalsIgnoreCase("FIN"));
            System.out.println("");
            System.out.println("");
            System.out.println(BOLD + "\t\t\t\t\t" + "ooOO Las líneas se han grabado en el fichero OOoo" + END);
            System.out.println("");
            System.out.println("");
            salida.flush();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            salida.close();
        }
    }
    public void Leer(){
        FileReader fr = null;
        try {
            fr = new FileReader("src/mitexto.txt");
            BufferedReader entrada = new BufferedReader(fr);
            System.out.println(BOLD + "El texto almacenado, una vez procesado:" + END);
            String cadena = entrada.readLine();
            while (cadena != null) {
                String t = cadena.replaceAll("(?iu)"+"a","i");
                String t2 = t.replaceAll("(?iu)"+"e","o");
                String primLetra = t2.substring(0, 1);
                String restLetras = t2.substring(1);
                primLetra = primLetra.toUpperCase();
                t2 = primLetra + restLetras;
                System.out.println("\t" + "En el fichero \uD83E\uDC16 " + cadena + " y en pantalla \uD83E\uDC16 " + t2);
                cadena = entrada.readLine();
            }
            System.out.println("");
            System.out.println("");
            System.out.println(BOLD + "\t\t\t\t\t\t\t" + "ooOO Se acabó el programa OOoo" + END);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}