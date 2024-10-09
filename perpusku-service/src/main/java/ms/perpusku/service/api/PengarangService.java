package ms.perpusku.service.api;

import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author MS
 */
public interface PengarangService {
    Optional<Pengarang> get(long id);
    List<Pengarang> getAll();
    long getTotalPengarang();
}
