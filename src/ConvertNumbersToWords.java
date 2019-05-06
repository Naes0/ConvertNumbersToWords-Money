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
        String cents = "";
        //calculate number of dots
        int numDots = NumberString.length() - NumberString.replace(".", "").length();
        if(numDots == 1)
        {
            String[] dollarsCents = NumberString.split("\\.");
            //check just in case a single '.' is entered.
            if (dollarsCents.length != 0)
            {
                try
                {
                    dollars = Integer.parseInt(dollarsCents[0]);
                    cents = dollarsCents[1];
                }
                catch(NumberFormatException e)
                {
                    throw new InvalidInputException("Error: Invalid input format, input is not an integer!");
                }
            }
            else
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
                cents = "0";
            }
            catch(NumberFormatException e)
            {
                throw new InvalidInputException("Error: Invalid input format, input is not an integer!");
            }
        }

        String dollarWords = dollarsToWords(dollars);
        String centWords = centsToWords(cents);
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
    
    private static String tensToWords(int twoDigit) throws InvalidInputException
    {
        String twoDigitWords = "";
        int length = Integer.toString(twoDigit).length();

        int firstInt = -1;
        int secondInt = -1;
        String first = "";
        String second = "";

        if(length == 2)
        {
            firstInt = twoDigit / 10;
            secondInt = twoDigit % 10;
            //double digits but 10 - 19
            if (firstInt == 1)
            {
                twoDigitWords = doubles.get(twoDigit);
            }
            //double digits 20 - 99
            else
            {
                if (secondInt == 0)
                {
                    twoDigitWords = tens.get(twoDigit);
                } else
                {
                    first = tens.get(firstInt * 10);
                    second = singles.get(secondInt);
                    twoDigitWords = first + "-" + second;
                }
            }
        }
        else if (length == 1)
        {
            if(twoDigit != 0)
            {
                twoDigitWords = singles.get(twoDigit);
            }
            else
            {
                twoDigitWords = "ZERO";
            }
        }
        else
        {
            throw new InvalidInputException("Error: Invalid cents length!");
        }
        return twoDigitWords;
    }

    private static String centsToWords(String cents) throws InvalidInputException
    {
        String centsWords = "";
        int length = cents.length();

        int firstInt = -1;
        int secondInt = -1;
        String first = "";
        String second = "";

        if(length == 2)
        {
            int asciiFirst = (int)cents.charAt(0);
            int asciiSecond = (int)cents.charAt(1);
            if(asciiFirst < 48 || asciiFirst > 57 || asciiSecond < 48 || asciiSecond > 57)
            {
                throw new InvalidInputException("Error: Not a valid number.");
            }

            firstInt = Character.getNumericValue(cents.charAt(0));
            secondInt = Character.getNumericValue(cents.charAt(1));

            // single digits 0 - 9
            if( firstInt == 0 )
            {
                centsWords = singles.get(secondInt);
            }
            //double digits but 10 - 19
            else if (firstInt == 1)
            {
                centsWords = doubles.get(firstInt*10 + secondInt);
            }
            //double digits 20 - 99
            else
            {
                if (secondInt == 0 )
                {
                    centsWords = tens.get(firstInt*10);
                }
                else
                {
                    if (tens.containsKey(firstInt*10) && singles.containsKey(secondInt))
                    {
                        first = tens.get(firstInt * 10);
                        second = singles.get(secondInt);
                        centsWords = first + "-" + second;
                    }
                    else
                    {
                        throw new InvalidInputException("Error: Not a valid number.");
                    }
                }
            }
        }
        else if (length == 1)
        {
            firstInt = Character.getNumericValue(cents.charAt(0));
            if(firstInt != 0)
            {
                if(tens.containsKey(firstInt*10))
                {
                    centsWords = tens.get(firstInt*10);
                }
                else
                {
                    throw new InvalidInputException("Error: Not a valid number.");
                }
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
        singles.put(0, "ZERO");
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

