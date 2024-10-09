package ms.perpusku.dao;

import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Peminjam;

/**
 *
 * @author MS
 */
public interface PeminjamDAO {
    boolean insert(Peminjam peminjam);
    List<Peminjam> get();
    Optional<Peminjam> get(long id);    
}
