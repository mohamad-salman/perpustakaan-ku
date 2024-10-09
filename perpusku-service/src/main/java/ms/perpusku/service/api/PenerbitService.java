package ms.perpusku.service.api;

import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Penerbit;

/**
 *
 * @author MS
 */
public interface PenerbitService {
    List<Penerbit> getAll();
    Optional<Penerbit> get(long id);
    long getTotalPenerbit();
}
