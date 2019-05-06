import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class ConvertNumbersToWordsTest
{
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void exampleDataTest() throws InvalidInputException
    {
        System.out.println("Running Example Test");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("123.45");
        assertEquals("ONE HUNDRED TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS", result);
    }

    //Below 0.0 input
    @Test
    public void belowRangeDollarTest() throws Exception
    {
        System.out.println("Below Range Test");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Please insert a positive number");
        ConvertNumbersToWords.ConvertNumbersToWords("-1.0");
    }

    @Test
    public void aboveRangeDollarTest() throws Exception
    {
        System.out.println("Above Range Test");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Invalid input format, input is not an integer!");
        ConvertNumbersToWords.ConvertNumbersToWords("3000000000.00");
    }

    @Test
    public void outOfRangeCentsTest()
    {

    }
}