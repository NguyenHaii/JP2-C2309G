package Service;

import Entity.ProductData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private List<ProductData> productDataList;

    public ProductService() {
        this.productDataList = new ArrayList<>();
    }

    public void readData(String filePath) {
        productDataList.clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    int productId = Integer.parseInt(parts[0].trim());
                    int clicks = Integer.parseInt(parts[1].trim());
                    int addToCart = Integer.parseInt(parts[2].trim());
                    int checkOut = Integer.parseInt(parts[3].trim());
                    LocalDate dateTime = LocalDate.parse(parts[4].trim(), formatter);

                    productDataList.add(new ProductData(productId, clicks, addToCart, checkOut, dateTime));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading product data file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing date or data: " + e.getMessage());
        }
    }

    public Map<String, double[]> calculateActionPercentagesByMonth() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter month (1-12): ");
        int month = sc.nextInt();
        Month selectedMonth = Month.of(month);

        Map<Integer, Integer> totalClicksPerID = productDataList.stream()
                .filter(product -> product.getDateTime().getMonth() == selectedMonth)
                .collect(Collectors.groupingBy(
                        ProductData::getProductId,
                        Collectors.summingInt(ProductData::getClicks)
                ));

        Map<Integer, Integer> totalAddToCartPerID = productDataList.stream()
                .filter(product -> product.getDateTime().getMonth() == selectedMonth)
                .collect(Collectors.groupingBy(
                        ProductData::getProductId,
                        Collectors.summingInt(ProductData::getAddToCart)
                ));

        Map<Integer, Integer> totalCheckOutPerID = productDataList.stream()
                .filter(product -> product.getDateTime().getMonth() == selectedMonth)
                .collect(Collectors.groupingBy(
                        ProductData::getProductId,
                        Collectors.summingInt(ProductData::getCheckOut)
                ));

        int totalClicks = totalClicksPerID.values().stream().mapToInt(Integer::intValue).sum();
        int totalAddToCart = totalAddToCartPerID.values().stream().mapToInt(Integer::intValue).sum();
        int totalCheckOut = totalCheckOutPerID.values().stream().mapToInt(Integer::intValue).sum();

        Map<String, double[]> percentagesMap = new HashMap<>();

        totalClicksPerID.forEach((id, totalClicksForProduct) -> {
            double clickPercentage = totalClicksForProduct * 100.0 / totalClicks;
            double addToCartPercentage = totalAddToCartPerID.getOrDefault(id, 0) * 100.0 / totalAddToCart;
            double checkOutPercentage = totalCheckOutPerID.getOrDefault(id, 0) * 100.0 / totalCheckOut;

            percentagesMap.put(String.valueOf(id), new double[]{clickPercentage, addToCartPercentage, checkOutPercentage});
        });
        sc.close();
        return percentagesMap;
    }
}

