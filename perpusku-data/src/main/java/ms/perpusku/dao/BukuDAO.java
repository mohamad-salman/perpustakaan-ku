package ms.perpusku.dao;

import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Buku;

/**
 *
 * @author MS
 */
public interface BukuDAO {
    List<Buku.Kategori> getAllKategori();
    Optional<Buku.Kategori> getKategori(String kode);
    
    void insert(Buku buku);
    List<Buku> get();
    Optional<Buku> get(long id);
}
