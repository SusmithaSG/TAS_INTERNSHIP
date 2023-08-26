import java.util.Scanner;

public class MultiplyNumbers {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            // Prompt the user to enter the first number

            System.out.print("Enter the first number: ");
            int firstNumber = scanner.nextInt();

            // Prompt the user to enter the second number
            System.out.print("Enter the second number: ");
            int secondNumber = scanner.nextInt();

            // Calculate the product of the two numbers
            int product = firstNumber * secondNumber;

            // Display the result
            System.out.println("The product of " + firstNumber + " and " + secondNumber + " is: " + product);
        }
    }
}
