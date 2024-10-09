package ms.perpusku.service.api;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Penerbit;
import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author MS
 */
public interface BukuService {
    int getTotalKategori();
    List<Buku.Kategori> getAllKategori();
    Optional<Buku.Kategori> getKategori(String id);
    
    List<Buku> get();
    Optional<Buku> get(long id);
    boolean simpanBuku(String judul, List<Pengarang> listPengarang,
            Penerbit penerbit, Year tahun, String kodeKategori, List<String> listIsbn);

    
}
