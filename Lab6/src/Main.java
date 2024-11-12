import Service.ProductService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "data/product.in.txt";
        String outputFilePath = "data/product.out.txt";

        ProductService productService = new ProductService();
        productService.readData(inputFilePath);

        Map<String, double[]> percentagesMap = productService.calculateActionPercentagesByMonth();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(String.format("%-15s | %-20s | %-20s | %-20s%n", "ProductCode", "Percentage Clicks", "Percentage AddToCart", "Percentage CheckOut"));
            writer.write("------------------------------------------------------------------------------------\n");

            if (percentagesMap != null && !percentagesMap.isEmpty()) {
                for (Map.Entry<String, double[]> entry : percentagesMap.entrySet()) {
                    String productCode = entry.getKey();
                    double[] percentages = entry.getValue();
                    writer.write(String.format("%-15s | %-20.2f | %-20.2f | %-20.2f%n", productCode, percentages[0], percentages[1], percentages[2]));
                }
                System.out.println("Results have been written to " + outputFilePath);
            } else {
                writer.write("No data to display. Percentages map is empty.\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
