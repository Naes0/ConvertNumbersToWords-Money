import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        boolean exit = false;
        while(exit != true)
        {
            try
            {
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter a number between 0.00 and 2,147,483,647.99 to convert or 'q' to exit:");
                String numberString = scan.nextLine();
                if(numberString.equals("q"))
                {
                    exit = true;
                    System.out.println("Exiting...");
                }
                else
                {
                    String result = ConvertNumbersToWords.ConvertNumbersToWords(numberString);
                    System.out.println(result);
                }
            }
            catch (InvalidInputException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Please enter a number between 0.00 - 2,147,483,647.99");
            }
        }
    }
}