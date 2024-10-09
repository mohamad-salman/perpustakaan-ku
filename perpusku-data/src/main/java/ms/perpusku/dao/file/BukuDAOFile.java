package ms.perpusku.dao.file;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.BukuDAO;
import ms.perpusku.dao.file.converter.BukuConverter;
import ms.perpusku.dao.file.converter.KategoriConverter;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Buku.Kategori;

/**
 *
 * @author MS
 */
public class BukuDAOFile implements BukuDAO {

    private static final String FILE_BUKU = "buku.csv";
    private static final String FILE_KATEGORI = "kategori.csv";

    @Override
    public List<Kategori> getAllKategori() {
        return DAOFile.getDataFromFile(FILE_KATEGORI, new KategoriConverter());
    }

    @Override
    public Optional<Buku.Kategori> getKategori(String kode) {
        return getAllKategori().stream()
                .filter(kategori -> kategori.getKode().equals(kode))
                .findAny();
    }

    @Override
    public void insert(Buku buku) {
        var lastId = get().stream()
                .sorted(Comparator.comparingLong(Buku::getId).reversed())
                .map(mapper -> mapper.getId())
                .findFirst();

        long newId = lastId.isPresent() ? (lastId.get() + 1) : 1;
        Buku bukuBaru = new Buku.Builder(buku.getJudul(), buku.getPengarang())
                .id(newId)
                .penerbit(buku.getPenerbit())
                .tahun(buku.getTahunTerbit())
                .kategori(buku.getKategori())
                .isbn(buku.getIsbn())
                .build();
        
        String csv = new BukuConverter().domainObjectToCSV(bukuBaru);
        
        DAOFile.insertDataToFile(csv, FILE_BUKU);
    }

    @Override
    public List<Buku> get() {
        return DAOFile.getDataFromFile(FILE_BUKU, new BukuConverter());
    }

    @Override
    public Optional<Buku> get(long id) {
        return get().stream()
                .filter(buku -> buku.getId() == id)
                .findAny();
    }

}
