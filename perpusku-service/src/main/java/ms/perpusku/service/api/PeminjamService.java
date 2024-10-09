package ms.perpusku.service.api;

import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Peminjam;

/**
 *
 * @author MS
 */
public interface PeminjamService {
    boolean simpanPeminjam(String nama, String telp);
    List<Peminjam> get();
    Optional<Peminjam> get(long id);
}
