package ms.perpusku.dao.file;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Peminjam;
import ms.perpusku.dao.PeminjamDAO;
import ms.perpusku.dao.file.converter.PeminjamConverter;

/**
 *
 * @author MS
 */
public class PeminjamDAOFile implements PeminjamDAO {

    private static final String FILE_PEMINJAM = "peminjam.csv";

    @Override
    public boolean insert(Peminjam peminjam) {
        long lastId = get().stream()
                .sorted(Comparator.comparingLong(Peminjam::getId).reversed())
                .map(mapper -> mapper.getId())
                .findFirst()
                .get();                   
        
        long newId = lastId + 1;
        Peminjam peminjamBaru = new Peminjam(newId, peminjam.getNama(), peminjam.getTelp());
        
        String csv = new PeminjamConverter().domainObjectToCSV(peminjamBaru);
        DAOFile.insertDataToFile(csv, FILE_PEMINJAM);
        
        return true;
    }

    @Override
    public List<Peminjam> get() {
        return DAOFile.getDataFromFile(FILE_PEMINJAM, new PeminjamConverter());
    }

    @Override
    public Optional<Peminjam> get(long id) {
        return get().stream()
                .filter(peminjam -> peminjam.getId() == id)
                .findAny();
    }
}
