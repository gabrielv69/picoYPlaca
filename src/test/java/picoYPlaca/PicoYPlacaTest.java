package picoYPlaca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)

public class PicoYPlacaTest {
    private String plate;
    private Date date,hour;
    private  PicoYPlaca sistema;


    @Parameterized.Parameters
    public static Iterable<Object[]> parameters() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
        List<Object[]> objects = new ArrayList<Object[]>();
        objects.add(new Object[]{"7841", dateFormat.parse("05/04/2021"), horaFormat.parse("08:30:00")}); //No road
        objects.add(new Object[]{"7842", dateFormat.parse("05/04/2021"), horaFormat.parse("09:31:00")});
        objects.add(new Object[]{"2593", dateFormat.parse("22/02/2022"), horaFormat.parse("17:15:02")}); //No road
        objects.add(new Object[]{"7841", dateFormat.parse("05/05/2017"), horaFormat.parse("08:30:00")});
        objects.add(new Object[]{"7841", dateFormat.parse("05/10/2021"), horaFormat.parse("08:30:00")});
        objects.add(new Object[]{"7841", dateFormat.parse("05/04/2018"), horaFormat.parse("10:30:00")});
        objects.add(new Object[]{"9584", dateFormat.parse("05/01/2021"), horaFormat.parse("19:29:59")}); //No road
        objects.add(new Object[]{"7841", dateFormat.parse("05/04/2021"), horaFormat.parse("22:30:00")});
        objects.add(new Object[]{"7845", dateFormat.parse("05/04/2021"), horaFormat.parse("08:30:00")});
        objects.add(new Object[]{"7849", dateFormat.parse("03/07/2018"), horaFormat.parse("17:45:12")});
        objects.add(new Object[]{"0040", dateFormat.parse("09/04/2021"), horaFormat.parse("16:02:01")});//No road
        return objects;
    }

    public PicoYPlacaTest(String plate, Date date, Date hour) {
        this.plate=plate;
        this.date=date;
        this.hour=hour;
    }


    @Before
    public  void setUp() {
        sistema = new PicoYPlaca();
    }


    @Test
    public void given_parameters_when_are_validated_then_road() {
        assertTrue(sistema.circula(plate,date,hour));
    }
}