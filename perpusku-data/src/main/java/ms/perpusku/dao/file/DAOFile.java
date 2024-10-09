package ms.perpusku.dao.file;

import ms.perpusku.dao.file.converter.CSVConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MS
 */
public class DAOFile {

    static <T> List<T> getDataFromFile(String namaFile, CSVConverter<T> domain) {
        List<T> list = new ArrayList<>();

        try {
            Path path = Paths.get("../resources/csv/" + namaFile);
            List<String> lines = Files.readAllLines(path);

            lines.forEach(row -> {
                T t = domain.csvToDomainObject(row);

                list.add(t);
            });
        } catch (IOException ex) {
            Logger.getLogger(DAOFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    static <T> void insertDataToFile(String csv, String namaFile) {
        StringBuilder sb = new StringBuilder();
        sb.append(csv);
        sb.append("\n");
        
        Path path = Paths.get("../resources/csv/" + namaFile);
        
        try {
            Files.writeString(path, sb.toString(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(DAOFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
