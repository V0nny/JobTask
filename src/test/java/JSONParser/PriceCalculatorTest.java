package JSONParser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriceCalculatorTest {

    @Test
    void findMedianOddArray() {
        List<Double> values = new ArrayList<>();
        {
            values.add(1d);
            values.add(3d);
            values.add(5d);
            values.add(2d);
            values.add(4d);
        }
        Collections.sort(values); //1 2 3 4 5

        Assertions.assertEquals(3, values.get(values.size()/2));
    }

    @Test
    void findMedianEvenArray() {
        List<Double> values = new ArrayList<>();
        {
            values.add(2d);
            values.add(3d);
            values.add(5d);
            values.add(3d);
            values.add(4d);
            values.add(10d);
        }
        Collections.sort(values); //2 3 3 4 5 10

        Assertions.assertEquals(3.5, (values.get((values.size() / 2) - 1) + values.get(values.size() / 2)) / 2);
    }

    @Test
    void findMediumPrice() {
        List<Double> values = new ArrayList<>();
    {
        values.add(2d);
        values.add(3d);
        values.add(5d);
        values.add(3d);
        values.add(4d);
        values.add(10d);
    }
        double totalAmount = 0d;

        for (Double value : values) {
            totalAmount += value;
        }

        Assertions.assertEquals(27.0/6.0, totalAmount/values.size());
    }

}
