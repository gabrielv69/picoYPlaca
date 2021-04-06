package picoYPlaca;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class PicoYPlaca {

    public boolean validarPlate(String inputPlate){
        System.out.println("Al ingresar a validar " + inputPlate);
        boolean vale=true;
        try {
            Integer.parseInt(inputPlate);
            if (inputPlate.length() != 4 ){
                System.out.println("Placa no válida, inserte una placa válida");
                vale= false;
                //ingresarplate();
            }
        }catch (Exception e){
            System.out.println("Ingrese un valor numérico");
            vale= false;
           // ingresarplate();
        }
        return vale;
    }

    public String ingresarplate(){
        Scanner entradaEscaner = new Scanner (System.in);
        System.out.println("Ingrese su número de placa:");
        String entradaTeclado= entradaEscaner.nextLine ();
        if (validarPlate(entradaTeclado)){
            System.out.println("Dentro if  " + entradaTeclado);
            return entradaTeclado;
        }else {
            System.out.println("Me pase al else  " + entradaTeclado);
            return ingresarplate();
        }
    }

    public int ingresarDate(){
        Scanner entradaEscaner = new Scanner (System.in);
        System.out.println("Ingrese fecha en formato dd/MM/yyyy:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String entrada= entradaEscaner.nextLine ();
        sdf.setLenient(false); //evito que fechas inválidas ( más de 30 días o 12 meses) sean aceptadas
        try {
            Date fecha = sdf.parse(entrada);
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            int dia =  c.get(Calendar.DAY_OF_WEEK);
            System.out.println(fecha);
            return dia;
        } catch (ParseException e) {
            System.out.println("Formato no válido");
            return ingresarDate();
        }
    }

    public void ingresarTime(){

        Scanner entradaEscaner = new Scanner (System.in);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Seleccione el tipo de hora:");
        System.out.println("1.Hora real");
        System.out.println("2.Hora específica (ingresada por teclado)");
        System.out.println("Opción: ");
        try {
            int entrada= entradaEscaner.nextInt();
            if (entrada==1){
                Date date = new Date();
                System.out.println("Hora actual: " + dateFormat.format(date));
                //System.out.println("Hora actual: " + dateFormat.format(fecha));
            }
            if (entrada==2){
                System.out.println("Ingrese hora en formato HH:mm:ss");
                entradaEscaner = new Scanner (System.in);
                String horaingresada= entradaEscaner.nextLine ();
                Date fecha = null;
                dateFormat.setLenient(false);
                try {
                    fecha = dateFormat.parse(horaingresada);
                    System.out.println("Hora actual: " + dateFormat.format(fecha));
                } catch (ParseException e) {
                    System.out.println("Formato no válido");
                    ingresarTime();
                }
            }
            if (entrada>2){
                System.out.println("Opción no válida");
                ingresarTime();
            }
        }catch (Exception e){
            System.out.println("Opción no válida");
            ingresarTime();
        }


    }


public boolean plate(String inputPlate, String day){
    System.out.println("Datos ingresados  " + inputPlate + "   dia  " + day);
    boolean circula = true;
    int lastDigit = Character.getNumericValue(inputPlate.charAt(3));
    System.out.println(lastDigit);

    if (day.equalsIgnoreCase("lunes")&&(  lastDigit==1 || lastDigit==2)){
        circula= false;
    }
    if (day.equalsIgnoreCase("martes")&&(  lastDigit==3 || lastDigit==4)){
        circula= false;
    }
    if (day.equalsIgnoreCase("miercoles")&&(  lastDigit==5 || lastDigit==6)){
        circula= false;
    }
    if (day.equalsIgnoreCase("jueves")&&(  lastDigit==7 || lastDigit==8)){
        circula= false;
    }
    if (day.equalsIgnoreCase("viernes")&&(  lastDigit==9 || lastDigit==0)){
        circula= false;
    }

    return circula;
}
}
