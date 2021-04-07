package picoYPlaca;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class PicoYPlaca {

    //Verificación si el String ingresado es numérico y de longitud 4
    public boolean validarPlate(String inputPlate) {
        boolean vale = true;
        try {
            Integer.parseInt(inputPlate); //Verifica número entero
            if (inputPlate.length() != 4) { //Verifica longitud
                System.out.println("Placa no válida, inserte una placa válida");
                vale = false;
            }
        } catch (Exception e) {
            System.out.println("Ingrese un valor numérico");
            vale = false;
        }
        return vale;
    }

    public String ingresarplate() {
        System.out.println("Ingrese su número de placa:");
        String plate = ingresoPorTeclado().nextLine();
        if (validarPlate(plate)) {
            return plate;
        } else {
            return ingresarplate(); //Hasta que no sea validado ingresamos nuevamente
        }
    }

    public Scanner ingresoPorTeclado() {
        return new Scanner(System.in);
    }

    //Ingreso por teclado una fecha con formato dd/MM/yyyy en String validada
    public Date ingresarDate() {
        System.out.println("Ingrese fecha en formato dd/MM/yyyy:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Formato de ingreso
        String date = ingresoPorTeclado().nextLine();
        dateFormat.setLenient(false); //evito que fechas inválidas ( más de 30 días o 12 meses) sean aceptadas
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Formato no válido");
            return ingresarDate(); //Recursividad hasta recibir fecha válida
        }
    }

    //Hora de entrada, puede ser hora actual (del sistema) o ingresada por el usuario
    public Date ingresarTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); //Formato de ingreso
        System.out.println("Seleccione el tipo de hora:");
        System.out.println("1.Hora real");
        System.out.println("2.Hora específica (ingresada por teclado)");
        System.out.println("Opción: ");
        try {
            int entrada = ingresoPorTeclado().nextInt();
            switch (entrada) {
                case 1:
                    Date hora = new Date();
                    return hora;
                case 2:
                    System.out.println("Ingrese hora en formato HH:mm:ss");
                    String horaingresada = ingresoPorTeclado().nextLine();
                    timeFormat.setLenient(false); //horas no válidas no son aceptadas más de 24 horas o 60 min/seg
                    try {
                        hora = timeFormat.parse(horaingresada);
                        return hora;
                    } catch (ParseException e) {
                        System.out.println("Formato no válido");
                        return ingresarTime();
                    }
                default:
                    System.out.println("Opción no válida");
                    return ingresarTime();

            }
        } catch (Exception e) {
            System.out.println("Opción no válida");
            return ingresarTime();
        }
    }

    //Determina si un carro circula mediante las reglas de pico y placa, con input número de placa, fecha y hora

    public boolean circula(String inputPlate, Date fecha, Date horaingresada) {
        DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss"); //Formato de hora

        System.out.println("\nDatos ingresados:\n" + "Placa: " + inputPlate +
                "\nFecha: " + new SimpleDateFormat("EEEE dd MMMM yyyy").format(fecha) +
                "\nHora: " + horaFormat.format(horaingresada));
        boolean circula = true;

        //Determinamos día de la semana a partir de la fecha
        Calendar calendar = Calendar.getInstance(); //Instancio un objeto Calendar
        calendar.setTime(fecha);
        int dia = calendar.get(Calendar.DAY_OF_WEEK);

        //Fin de semana no aplica el pico y placa
        if (dia == 7 || dia == 1) {
            System.out.println("Libre circulación el fin de semana");
            return true;
        }

        //Determinamos si la consulta está dentro de las horas que aplica el pico y placa
        try {
            Date inicialMorning = horaFormat.parse("07:00:00");
            Date finalMorning = horaFormat.parse("09:30:00");
            Date inicialAfternoon = horaFormat.parse("16:00:00");
            Date finalAfternoon = horaFormat.parse("19:30:00");

            //  Chequeo horas
            if ((horaingresada.after(inicialMorning) && horaingresada.before(finalMorning)) || (horaingresada.after(inicialAfternoon) && horaingresada.before(finalAfternoon))) {

                //Chequeo placas
                int lastDigit = Character.getNumericValue(inputPlate.charAt(3)); //Obtenemos último dígito de la placa
                dia--; //Le resto uno para coincidir Lunes=1
                //Verificación de Lunes A Jueves
                for (int i = 1; i < 5; i++) {
                    if (dia == i && (lastDigit == (i + i) - 1 || lastDigit == i + i))
                        circula = false;
                }
                //Verificación Viernes
                if (dia == 5 && (lastDigit == 9 || lastDigit == 0))
                    circula = false;
            } else {
                System.out.println("\n Circulación Libre, hora no aplica Pico y Placa");
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return circula;
    }
}
