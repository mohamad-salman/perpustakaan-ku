package ms.perpusku.dao.file;

import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.PenerbitDAO;
import ms.perpusku.dao.file.converter.PenerbitConverter;
import ms.perpusku.domain.model.Penerbit;

/**
 *
 * @author MS
 */
public class PenerbitDAOFile implements PenerbitDAO {

    private static final String FILE_PENERBIT = "penerbit.csv";

    @Override
    public List<Penerbit> get() {
        return DAOFile.getDataFromFile(FILE_PENERBIT, new PenerbitConverter());
    }

    @Override
    public Optional<Penerbit> get(long id) {
        return get().stream()
                .filter(p -> p.getId() == id)
                .findAny();
    }

    @Override
    public boolean insert(Penerbit penerbit) {
        String csv = new PenerbitConverter().domainObjectToCSV(penerbit);
        DAOFile.insertDataToFile(csv, FILE_PENERBIT);
        
        return true;
    }

}
