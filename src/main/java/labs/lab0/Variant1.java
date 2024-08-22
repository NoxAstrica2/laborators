package labs.lab0;

public class Variant1 {

    public enum DAY_OF_WEEK {
        MONDAY, TUESDAY, WEDNESDAY
    }

    public int integerNumbersTask(int k) {
        return 0;
    }

    /**
     * @param number
     * @return true, if number is possitive
     * Дано ціле число, що лежить в діапазоні 0..1000. Вивести його рядок-опис виду «парне двоціфрове число», «непарне трицифрове число»; і т. д.
     */
    public boolean booleanTask(int number) {
        return false;
    }


    /**
     * @param parameter is integer number
     * @return transformed number
    * Дано ціле число, що лежить в діапазоні 0..999. Вивести його рядок-опис виду «парне двоціфрове число», «непарне трицифрове число»; і т. д.
     */
    public String ifTask(int parameter) {
        if (parameter < 0 || parameter >= 1000){
            throw new RuntimeException("parameter should be in the range from 0 to 999 inclusive");
        }
        String parity = (parameter % 2 == 0) ? "even" : "odd";
        if (parameter < 10) {
            return parity + " one-digit number";
        }
        if (parameter < 100) {
            return parity + " two-digit number";
        }
        return parity + " three-digit number";
    }


    /**
     * @param number1
     * @return day of week day represented number1
     */
    public DAY_OF_WEEK switchTask(int number1) {
        return DAY_OF_WEEK.MONDAY;
    }


    /**
     * @param n is integer number
     * @return approximated value of exp(1)
     */
    public double forTask(int n) {
        assert n > 0 : "Argument should be more than zero";
        return 0;
    }


    public int whileTask(int a, int b) {
        assert (a > 0 && b > 0) : "Argument should be more than zero";
        return 0;
    }

    /**
     *
     * @param array
     * @return
     * Для заданого масиву знаходяться номери тих елементів масиву, які більші за свого правого сусіда,
     * та кількість таких елементів.
     */
    public int[] arrayTask(double[] array) {
        int[] temp = new int[array.length];
        int count = 0;
        for (int i=0; i <array.length-1; i++){
            if (array[i] > array[i+1]){
                temp[count++] = i;
            }
        }
        int[] result = new int[count+1];
        for (int i=0; i< count; i++){
            result[i] = temp[i];
        }
        result[count] = count;
        return result;
    }

    /**
     * @param array
     * @param k1
     * @param k2
     * @return transformed array where row with indexes k1 and k2 was changed
     */
    public int[][] twoDimensionArrayTask(int[][] array, int k1, int k2) {
        //return null;
        return array;
    }

    public static void main(String... strings) {
        System.out.println("Start of zero lab");
        System.out.println("Done!!!");
        String expected = "парне двоцифрове число";
        String actual = new Variant1().ifTask(24);
        System.out.println((expected.equals(actual)) + ", expected = " + expected + ", actual = " + actual);
    }


}
