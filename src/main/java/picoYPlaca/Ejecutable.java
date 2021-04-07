package picoYPlaca;

public class Ejecutable {
    public static void main (String[] args){
        PicoYPlaca sistema = new PicoYPlaca();
        String resultado = sistema.circula(sistema.ingresarplate(),sistema.ingresarDate(),sistema.ingresarTime()) ? "\n Puede circular" : "\n No puede circular";
        System.out.println(resultado);
    }
}
