import java.util.Scanner;

public class CurrencyConverter {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter the source currency (USD, EUR, JPY): ");
        String sourceCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency (USD, EUR, JPY): ");
        String targetCurrency = scanner.next().toUpperCase();

        double convertedAmount = convertCurrency(amount, sourceCurrency, targetCurrency);
        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

        scanner.close();
    }

    public static double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {
        // Exchange rates (as of knowledge cutoff date in September 2021)
        double usdToEurRate = 0.91;
        double usdToJpyRate = 144.72;
        double eurToUsdRate = 1 / usdToEurRate;
        double eurToJpyRate = 159.09;
        double jpyToUsdRate = 0.0069;
        double jpyToEurRate = 0.0063;

        double convertedAmount = 0.0;

        if (sourceCurrency.equals("USD") && targetCurrency.equals("EUR")) {
            convertedAmount = amount * usdToEurRate;
        } else if (sourceCurrency.equals("USD") && targetCurrency.equals("JPY")) {
            convertedAmount = amount * usdToJpyRate;
        } else if (sourceCurrency.equals("EUR") && targetCurrency.equals("USD")) {
            convertedAmount = amount * eurToUsdRate;
        } else if (sourceCurrency.equals("EUR") && targetCurrency.equals("JPY")) {
            convertedAmount = amount * eurToJpyRate;
        } else if (sourceCurrency.equals("JPY") && targetCurrency.equals("USD")) {
            convertedAmount = amount * jpyToUsdRate;
        } else if (sourceCurrency.equals("JPY") && targetCurrency.equals("EUR")) {
            convertedAmount = amount * jpyToEurRate;
        } else {
            System.out.println("Invalid source or target currency.");
            System.exit(1);
        }

        return convertedAmount;
    }
}