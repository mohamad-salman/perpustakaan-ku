package ms.perpusku.dao;

import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author MS
 */
public interface PengarangDAO {
    boolean insert(Pengarang pengarang);
    List<Pengarang> get();
    Optional<Pengarang> get(long id);
}
