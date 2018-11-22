package vinkkiloodi.io;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Niko
 */
public class StubIOTest {

    private StubIO io;
    private List<String> input;

    @Before
    public void setUp() {
        this.io = new StubIO(new ArrayList<>());
        this.input = new ArrayList();
    }

    @Test
    public void getOutputTest() {
        input.add("Ensimmäinen");
        input.add("Toinen");
        input.add("Kolmas");

        for (String s : input) {
            io.printLine(s);
        }

        boolean isOk = true;

        List<String> output = io.getOutput();

        for (int i = 0; i < input.size(); i++) {
            if (!output.get(i).equals(input.get(i))) {
                isOk = false;
            }
        }

        assert (isOk);
    }

    @Test
    public void nextIntTest1() {
        input.add("21");

        io = new StubIO(input);

        assertEquals(io.nextInt(), 21);
    }

    @Test
    public void nextLineTest() {
        input.add("Ensimmäinen");
        input.add("Toinen");
        input.add("Kolmas");

        io = new StubIO(input);

        assertEquals(io.nextLine(), "Ensimmäinen");
    }
}
