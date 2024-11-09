import Entity.Customer;

import javax.management.StringValueExp;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Read data in file
        String sysPath = System.getProperty("user.dir");
        String fileInPath = sysPath.replace("/" , "\\") + "/data/customer.in.txt";
        String fileOutPath = sysPath.replace("/","\\")  + "/data/customer.out.txt/";
        List<Customer> customers = new ArrayList<>();
        // path fit Linux and Os , Convert to Windows
        //Reading data from fileInPath
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInPath));
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null){
                Customer customer = new Customer();
                if (!lineData.isEmpty()){
                    String[] arData = lineData.split(";");
                    customer.setId(Integer.parseInt(String.valueOf(arData[0])));
                    customer.setCode(String.valueOf(arData[1]));
                    customer.setName(String.valueOf(arData[2]));
                }
                customers.add(customer);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
//Sorted and display
        Set<Customer> customerSet = customers.stream()
                .sorted(Comparator.comparing(Customer::getName).reversed())
                .collect(Collectors.toSet());
        customerSet.forEach(customer -> System.out.println(customer.toString("~")));

        //Write data to file
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOutPath));
            customerSet.stream()
                    .peek(customer -> {
                        try {
                            String linetoWrite = customer.toString(":=:");
                            bufferedWriter.write(linetoWrite);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                    }).collect(Collectors.toSet());
        }catch (IOException e){
            System.out.println(e.getMessage());
    }
}}