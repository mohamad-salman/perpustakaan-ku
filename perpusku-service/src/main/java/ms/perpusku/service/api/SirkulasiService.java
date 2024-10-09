package ms.perpusku.service.api;

import java.util.List;
import java.util.stream.Stream;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Sirkulasi;

/**
 *
 * @author MS
 */
public interface SirkulasiService {
    boolean simpanPeminjaman(long idPeminjam, List<Buku> listBuku);
    boolean simpanPengembalian(List<Buku> listBuku);
    boolean isBukuDiPinjam(Buku buku);
    long getTotalBukuDiPinjam();
    Stream<Sirkulasi.Rincian> getBukuDiPinjam();
}
