package ms.perpusku.dao.file;

import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.PengarangDAO;
import ms.perpusku.dao.file.converter.PengarangConverter;
import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author MS
 */
public class PengarangDAOFile implements PengarangDAO {

    private static final String FILE_PENGARANG = "pengarang.csv";

    @Override
    public List<Pengarang> get() {
        return DAOFile.getDataFromFile(FILE_PENGARANG, new PengarangConverter());
    }

    @Override
    public Optional<Pengarang> get(long id) {
        return get().stream()
                .filter(p -> p.getId() == id)
                .findAny();
    }

    @Override
    public boolean insert(Pengarang pengarang) {
        String csv = new PengarangConverter().domainObjectToCSV(pengarang);
        DAOFile.insertDataToFile(csv, FILE_PENGARANG);
        
        return true;
    }

}
