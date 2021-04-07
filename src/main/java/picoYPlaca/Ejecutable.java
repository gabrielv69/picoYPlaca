package picoYPlaca;

import java.text.SimpleDateFormat;

public class Ejecutable {
    public static void main (String[] args){
        System.out.println("******************  Pico y Placa Predictor  ******************\n Ingreso de Datos");
        PicoYPlaca sistema = new PicoYPlaca();
        String resultado = sistema.circula(sistema.ingresarplate(),sistema.ingresarDate(),sistema.ingresarTime()) ? "\n Puede circular" : "\n No puede circular";
        System.out.println(resultado);
    }
}
