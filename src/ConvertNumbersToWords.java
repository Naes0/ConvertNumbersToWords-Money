import java.util.HashMap;
import java.util.Map;

public class ConvertNumbersToWords
{
    private static Map<Integer,String> singles = new HashMap<Integer,String>();
    private static Map<Integer,String> doubles = new HashMap<Integer,String>();
    private static Map<Integer,String> tens = new HashMap<Integer,String>();

    public static String ConvertNumbersToWords(String NumberString) throws InvalidInputException
    {
        initMaps();
        String words = "";
        int dollars = -1;
        int cents = -1;
        //calculate number of dots
        int numDots = NumberString.length() - NumberString.replace(".", "").length();
        if(numDots == 1)
        {
            String[] dollarsCents = NumberString.split("\\.");
            try
            {
                dollars = Integer.parseInt(dollarsCents[0]);
                cents = Integer.parseInt(dollarsCents[1]);
            }
            catch(NumberFormatException e)
            {
                throw new InvalidInputException("Error: Invalid input format, input is not an integer!");
            }
        }
        else if (numDots > 1)
        {
            throw new InvalidInputException("Error: Invalid input format, input is not an integer!");
        }
        else
        {
            try
            {
                dollars = Integer.parseInt(NumberString);
                cents = 0;
            }
            catch(NumberFormatException e)
            {
                throw new InvalidInputException("Error: Invalid input format, input is not an integer!");
            }
        }

        String centWords = centsToWords(cents);
        String dollarWords = dollarsToWords(dollars);
        words = dollarWords + " DOLLARS AND " + centWords + " CENTS";
        return words;
    }

    private static String dollarsToWords(int dollars) throws InvalidInputException
    {
        String dollarWords = "";

        if(dollars == 0)
        {
            dollarWords = "ZERO";
        }
        if(dollars < 0)
        {
            throw new InvalidInputException("Error: Please insert a positive number");
        }
        if((dollars / 1000000000) > 0)
        {
            dollarWords += dollarsToWords(dollars / 1000000000) + " BILLION ";
            dollars = dollars % 1000000000;
        }
        if((dollars / 1000000) > 0)
        {
            dollarWords += dollarsToWords(dollars / 1000000) + " MILLION ";
            dollars = dollars % 1000000;
        }
        if((dollars / 1000) > 0)
        {
            dollarWords += dollarsToWords(dollars / 1000) + " THOUSAND ";
            dollars = dollars % 1000;
        }
        if((dollars / 100) > 0)
        {
            dollarWords += dollarsToWords(dollars / 100) + " HUNDRED ";
            dollars = dollars % 100;
        }
        //base case
        if(dollars > 0)
        {
            dollarWords += tensToWords(dollars);
        }
        return dollarWords;
    }
    
    private static String tensToWords(int cents) throws InvalidInputException
    {
        String centsWords = "";
        int length = Integer.toString(cents).length();

        int firstInt = -1;
        int secondInt = -1;
        String first = "";
        String second = "";

        if(length == 2)
        {
            firstInt = cents / 10;
            secondInt = cents % 10;
            //double digits but 10 - 19
            if (firstInt == 1)
            {
                centsWords = doubles.get(cents);
            }
            //double digits 20 - 99
            else
            {
                if (secondInt == 0)
                {
                    centsWords = tens.get(cents);
                } else
                {
                    first = tens.get(firstInt * 10);
                    second = singles.get(secondInt);
                    centsWords = first + "-" + second;
                }
            }
        }
        else if (length == 1)
        {
            if(cents != 0)
            {
                centsWords = singles.get(cents);
            }
            else
            {
                centsWords = "ZERO";
            }
        }
        else
        {
            throw new InvalidInputException("Error: Invalid cents length!");
        }
        return centsWords;
    }

    private static String centsToWords(int cents) throws InvalidInputException
    {
        String centsWords = "";
        int length = Integer.toString(cents).length();

        int firstInt = -1;
        int secondInt = -1;
        String first = "";
        String second = "";

        if(length == 2)
        {
            firstInt = cents / 10;
            secondInt = cents % 10;
            //double digits but 10 - 19
            if (firstInt == 1)
            {
                centsWords = doubles.get(cents);
            }
            //double digits 20 - 99
            else
            {
                if (secondInt == 0)
                {
                    centsWords = tens.get(cents);
                }
                else
                {
                    first = tens.get(firstInt * 10);
                    second = singles.get(secondInt);
                    centsWords = first + "-" + second;
                }
            }
        }
        else if (length == 1)
        {
            if(cents != 0)
            {
                centsWords = tens.get(cents*10);
            }
            else
            {
                centsWords = "ZERO";
            }
        }
        else
        {
            throw new InvalidInputException("Error: Invalid cents length!");
        }
        return centsWords;
    }

    private static void initMaps()
    {
        //Initialise Singles Map
        singles.put(1, "ONE");
        singles.put(2, "TWO");
        singles.put(3, "THREE");
        singles.put(4, "FOUR");
        singles.put(5, "FIVE");
        singles.put(6, "SIX");
        singles.put(7, "SEVEN");
        singles.put(8, "EIGHT");
        singles.put(9, "NINE");

        //Initialise Doubles Map
        doubles.put(10, "TEN");
        doubles.put(11, "ELEVEN");
        doubles.put(12, "TWELVE");
        doubles.put(13, "THIRTEEN");
        doubles.put(14, "FOURTEEN");
        doubles.put(15, "FIFTEEN");
        doubles.put(16, "SIXTEEN");
        doubles.put(17, "SEVENTEEN");
        doubles.put(18, "EIGHTEEN");
        doubles.put(19, "NINETEEN");

        //Initialise Tens Map
        tens.put(10, "TEN");
        tens.put(20, "TWENTY");
        tens.put(30, "THIRTY");
        tens.put(40, "FORTY");
        tens.put(50, "FIFTY");
        tens.put(60, "SIXTY");
        tens.put(70, "SEVENTY");
        tens.put(80, "EIGHTY");
        tens.put(90, "NINETY");
    }
}

