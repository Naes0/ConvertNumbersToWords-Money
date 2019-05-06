import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(ConvertNumbersToWordsTest.class);

        for (Failure failure : result.getFailures())
        {
            System.out.println("Test: " + failure.getTestHeader() + "   Failed");
            System.out.println(failure.toString());
            System.out.println(failure.getTrace());
        }
        System.out.println("\nSuccessful: " + result.wasSuccessful());
        System.out.println((result.getRunCount() - result.getFailureCount()) + "/" + result.getRunCount() + " tests passed");
        System.out.println("Runtime: " + result.getRunTime() + "ms");
    }
}
