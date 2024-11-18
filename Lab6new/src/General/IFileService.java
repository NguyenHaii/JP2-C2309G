package General;

import java.util.List;

public interface IFileService<T> {
    List<T> readFile(String filePath);
    void writeFile(String filePath);
}