package Service;

import Entity.CRIndex;
import Entity.StaticsView;
import General.IFileService;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileService implements IFileService<StaticsView> {
    private final List<CRIndex> crIndexes = new ArrayList<>();

    public void addCRIndex(CRIndex index) {
        crIndexes.add(index);
    }

    @Override
    public List<StaticsView> readFile(String filePath) {
        List<StaticsView> staticsViews = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    StaticsView view = new StaticsView(
                            Integer.parseInt(parts[0]),
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            LocalDate.parse(parts[4])
                    );
                    staticsViews.add(view);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return staticsViews;
    }

    @Override
    public void writeFile(String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            String header = String.format("%-5s | %-5s | %-5s | %-10s | %-10s | %-10s",
                    "ID", "Month", "Year", "CRView", "CRAddToCart", "CRCheckOut");
            bufferedWriter.write(header);
            bufferedWriter.newLine();
            bufferedWriter.write("---------------------------------------------------------------");
            bufferedWriter.newLine();

            for (CRIndex crStatics : crIndexes) {
                String lineWrite = String.format("%-5d | %-5d | %-5d | %-10.2f | %-10.2f | %-10.2f",
                        crStatics.getId(),
                        crStatics.getMonth(),
                        crStatics.getYear(),
                        crStatics.getCrViews(),
                        crStatics.getCrAddToCart(),
                        crStatics.getCrCheckOut());
                bufferedWriter.write(lineWrite);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

}

