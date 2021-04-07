package picoYPlaca;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class PlateValidationTest {

    private String plate;
    private  PicoYPlaca sistema;


    @Parameterized.Parameters
    public static Iterable<Object[]> parameters() throws ParseException {

        List<Object[]> objects = new ArrayList<Object[]>();
        objects.add(new Object[]{"7841"}); //Válida
        objects.add(new Object[]{"3047"}); //Válida
        objects.add(new Object[]{"asdf"});
        objects.add(new Object[]{"plate"});
        objects.add(new Object[]{"784r"});
        objects.add(new Object[]{"5s84"});
        objects.add(new Object[]{"7ss1"});
        objects.add(new Object[]{"788d"});
        objects.add(new Object[]{"75.4"});
        objects.add(new Object[]{"03.24"});
        objects.add(new Object[]{"7848812"});
        objects.add(new Object[]{"1658"}); //Válida
        objects.add(new Object[]{"ab54"});
        objects.add(new Object[]{"as47"});
        return objects;
    }

    public PlateValidationTest(String plate) { this.plate=plate; }

    @Before
    public  void setUp() { sistema = new PicoYPlaca(); }

    @Test
    public void given_Strings_when_are_incorrect_then_false() {
        assertFalse(sistema.validarPlate(plate));
    }

}