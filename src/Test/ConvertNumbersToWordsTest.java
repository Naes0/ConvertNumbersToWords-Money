import org.junit.Test;
import static org.junit.Assert.*;

public class ConvertNumbersToWordsTest
{
    @Test
    public void exampleDataTest() throws InvalidInputException
    {
        System.out.println("Running Example Test");
        String result = ConvertNumbersToWords.ConvertNumbersToWords("123.45");
        assertEquals("ONE HUNDRED TWENTY-THREE DOLLARS AND FORTY-FIVE CENT=", result);
        System.out.println("Test Success");
    }

    @Test
    public void outOfRangeDollarTest()
    {
        System.out.println("Out of Range Test");
    }

    @Test
    public void outOfRangeCentsTest()
    {

    }
}