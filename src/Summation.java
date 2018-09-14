import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class Summation
{

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("summation.dat"));
        Integer output = 0;

        do {
            String input = file.nextLine();

            //value constraints
            int start = Integer.parseInt(input.substring(1, input.indexOf(',')));
            int end = Integer.parseInt(input.substring(input.indexOf(',') + 1, input.indexOf(')')));

            for (int i = start; i <= end; i++)
            {
                //simplifies string to an equation
                String part1 = input.substring(input.indexOf(')') + 3, input.indexOf("x2") + 1);
                String part2 = input.substring(input.indexOf('+') + 2);

                //replaces 'x' with value
                part1 = part1.replaceFirst("x","*" + (i*i));
                part2 = part2.replaceFirst("x", "*" + i);

                //removes spaces and computes
                String toCompute = part1 + " + " + part2;
                toCompute = toCompute.replaceAll(" ","");

                //adds the result of every compute
                output += compute(toCompute);
            }

            out.println(output);
            output = 0;

        } while (file.hasNextLine());

    }

    static int compute(String equation)
    {
        int result = 0;
        String [] byAddition = equation.split("\\+");

        for (String addition : byAddition)
        {
            String [] byMultiply = addition.split("\\*");
            int multiplyResult = 1;

            for (String multiply : byMultiply)
            {
                multiplyResult *= Integer.parseInt(multiply);
            }

            result += multiplyResult;
        }

        return result;
    }
}
