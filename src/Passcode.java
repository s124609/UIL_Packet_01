import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class Passcode
{
    private static char[] vowels = {'a','e','i','o','u','A','E','I','O','U',',','.',' '};
    private static boolean[] prime;

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(new File("passcode.dat"));

        String inputString = file.nextLine();
        char[] inputChar = inputString.toCharArray();

        int pos = 0;
        prime = makePrime();

        do{
            //even && vowel
            if (pos%2==0 && isVowel(inputChar[pos]))
                inputChar[pos] = (("" + inputChar[pos]).toUpperCase()).charAt(0);

            //prime && consonant
            if (isPrime(pos) && !isVowel(inputChar[pos]))
            {
                //lowercase
                if (inputChar[pos] >= 97 && inputChar[pos] <= 122)
                {
                    do{
                        inputChar[pos] += 1;

                        if (inputChar[pos] >= 122)
                            inputChar[pos] = 98;

                        if (isVowel(inputChar[pos]))
                            inputChar[pos] += 1;

                    }while (isVowel(inputChar[pos]));

                }

                //uppercase
                else if (inputChar[pos] >= 65 && inputChar[pos] <= 90)
                {
                    do{
                        inputChar[pos] += 1;

                        if (inputChar[pos] == 90)
                            inputChar[pos] = 66;

                        if (isVowel(inputChar[pos]))
                            inputChar[pos] += 1;

                    }while (isVowel(inputChar[pos]));

                }
            }

            //odd && consonant
            else if (pos%2!=0 && !isVowel(inputChar[pos]))
                inputChar[pos] = '*';

            pos++;

        }while(pos < inputChar.length);

        for (int i = 0; i < inputChar.length; i++)
        {
            if (inputChar[i] == ' ')
                i++;
            out.print(inputChar[i] + "");
        }
    }

    static boolean[] makePrime()
    {

        int max = 50;
        boolean[] primeAr = new boolean[max];

        for (int next = 2; next <= Math.sqrt(max); next++)
        {
            int i = 0;
            while (next*next + i < primeAr.length)
            {
                primeAr[next*next + i] = true;
                i+=next;

            }

        }

        primeAr[1] = true; primeAr[0] = true;

        return primeAr;

    }

    static boolean isPrime(int position)
    {
        return !prime[position];
    }

    static boolean isVowel(char letter)
    {
        for (char ref : vowels)
        {
            if (letter == ref)
                return true;

        }

        return false;

    }
}


