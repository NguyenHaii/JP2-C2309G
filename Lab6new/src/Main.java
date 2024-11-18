
import Entity.CRIndex;
import Service.CRService;
import Service.FileService;
import Entity.CRStatics;
import Entity.StaticsView;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String sysPath = System.getProperty("user.dir");
        String fileInPath = sysPath.replace("/", "\\") + "\\data\\Statics.view.txt";
        String fileOutPath = sysPath.replace("/", "\\") + "\\data\\StaticsCR.view.txt";

        System.out.println("Reading file from: " + fileInPath);

        FileService fileService = new FileService();
        List<StaticsView> staticsViewList = fileService.readFile(fileInPath);

        if (staticsViewList.isEmpty()) {
            System.err.println("No data found in input file. Exiting program.");
            return;
        }
        CRService crService = new CRService(staticsViewList);

        Map<CRStatics, CRStatics> dataCRS = crService.dataCRS();
        dataCRS.values().forEach(crStatics -> {
            CRIndex crIndex = crService.calculateCRIndex(crStatics);
            fileService.addCRIndex(crIndex);
        });

        fileService.writeFile(fileOutPath);
        System.out.println("Data written to file: " + fileOutPath);
    }
}
