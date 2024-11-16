import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = readProducts("data/product.in.txt");
        List<OrderDetail> orderDetails = readOrderDetails("data/orderDetail.in.txt");

        int orderId = 1;
        Order order = new Order(orderId, orderDetails, products);
        displayOrderDetail(order, "data/orderDetail.out.txt");
    }

    private static List<OrderDetail> readOrderDetails(String filePath) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    int id = Integer.parseInt(parts[0]);
                    int orderId = Integer.parseInt(parts[1]);
                    int productId = Integer.parseInt(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    orderDetails.add(new OrderDetail(id, orderId, productId, quantity, price));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading order details file: " + e.getMessage());
        }
        return orderDetails;
    }

    private static List<Product> readProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    products.add(new Product(id, name, price, quantity));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading products file: " + e.getMessage());
        }
        return products;
    }

    private static void displayOrderDetail(Order order, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Order Details for Order ID: " + order.getId() + "\n");
            writer.write("+-----------------------------------------------------+\n");
            writer.write("| Product            | Quantity | Price  | Total      |\n");
            writer.write("+-----------------------------------------------------+\n");

            double totalAmount = 0;

            for (OrderDetail detail : order.getOrderDetails().stream().filter(od -> od.getOrderId() == order.getId()).collect(Collectors.toList())) {
                Product product = order.getProducts().stream().filter(p -> p.getId() == detail.getProductId()).findFirst().orElse(null);
                if (product != null) {
                    double totalProductPrice = detail.getQuantity() * detail.getPrice();

                    writer.write(String.format("| %-18s | %-8d | %-5.2f | %-10.2f |\n",
                            product.getName(), detail.getQuantity(), detail.getPrice(), totalProductPrice));

                    totalAmount += totalProductPrice;
                }
            }
            writer.write("+-----------------------------------------------------+\n");
            writer.write(String.format("| Total Amount Due:  |          |        | %-10.2f |\n", totalAmount));
            writer.write("+-----------------------------------------------------+\n");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
