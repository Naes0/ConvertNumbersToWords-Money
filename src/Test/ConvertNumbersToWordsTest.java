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
        System.out.println("Running Example Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("123.45");
        assertEquals("ONE HUNDRED TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS", result);
    }

    //Testing input below 0.0
    @Test
    public void belowRangeDollarTest() throws Exception
    {
        System.out.println("Below Range Dollars Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Please insert a positive number");
        ConvertNumbersToWords.ConvertNumbersToWords("-1.0");
    }

    //testing input above 2,147,483,647.99
    @Test
    public void aboveRangeDollarTest() throws Exception
    {
        System.out.println("Above Range Dollars Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Invalid input format, input is not an integer!");
        ConvertNumbersToWords.ConvertNumbersToWords("3000000000.00");
    }

    //testing character input for dollar via 'a'
    @Test
    public void nonNumberDollarTest() throws Exception
    {
        System.out.println("Non Number Dollars Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Invalid input format, input is not an integer!");
        ConvertNumbersToWords.ConvertNumbersToWords("a.0");
    }

    //testing input below as single for cents
    @Test
    public void oneDigitCentsTest() throws InvalidInputException
    {
        System.out.println("Below Range Cents Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("1.1");
        assertEquals("ONE DOLLARS AND TEN CENTS", result);
    }

    //testing input below as double digit for cents
    @Test
    public void twoDigitCentsTest() throws InvalidInputException
    {
        System.out.println("Two Digit Cents Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("1.10");
        assertEquals("ONE DOLLARS AND TEN CENTS", result);
    }

    @Test
    public void singleMapCentsTest() throws InvalidInputException
    {
        System.out.println("Single Map Cents Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("1.01");
        assertEquals("ONE DOLLARS AND ONE CENTS", result);
    }

    @Test
    public void doubleMapCentsTest() throws InvalidInputException
    {
        System.out.println("Double Map Cents Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("1.11");
        assertEquals("ONE DOLLARS AND ELEVEN CENTS", result);
    }

    @Test
    public void tensMapCentsTest() throws InvalidInputException
    {
        System.out.println("Tens Map Cents Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("1.20");
        assertEquals("ONE DOLLARS AND TWENTY CENTS", result);
    }

    @Test
    public void combinationMapCentsTest() throws InvalidInputException
    {
        System.out.println("Combination Map Cents Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("1.79");
        assertEquals("ONE DOLLARS AND SEVENTY-NINE CENTS", result);
    }

    //testing input below 0 for cents
    @Test
    public void belowRangeCentsTest() throws Exception
    {
        System.out.println("Below Range Cents Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Not a valid number.");
        ConvertNumbersToWords.ConvertNumbersToWords("1.-1");
    }

    //testing above 99 for cents also counts as invalid length since it hits 3 digits
    @Test
    public void aboveRangeCentsTest() throws Exception
    {
        System.out.println("Above Range Cents Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Invalid cents length!");
        ConvertNumbersToWords.ConvertNumbersToWords("1.100");
    }

    //testing character input for cents 1st digit
    @Test
    public void non1NumberCentsTest() throws Exception
    {
        System.out.println("Non Number 1st Digit Cents Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Not a valid number.");
        ConvertNumbersToWords.ConvertNumbersToWords("1.a0");
    }

    //testing character input for cents 1st digit
    @Test
    public void non2NumberCentsTest() throws Exception
    {
        System.out.println("Non Number 2nd Digit Cents Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Not a valid number.");
        ConvertNumbersToWords.ConvertNumbersToWords("1.0a");
    }

    //testing multiple dots in input
    @Test
    public void multipleDotsTest() throws Exception
    {
        System.out.println("Multiple Dots Test...");
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage("Error: Invalid input format, input is not an integer!");
        ConvertNumbersToWords.ConvertNumbersToWords("1.0.0");
    }

    //testing multiple dots in input
    @Test
    public void noDotsTest() throws Exception
    {
        System.out.println("No Dots Test...");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("1");
        assertEquals("ONE DOLLARS AND ZERO CENTS", result);
    }
}