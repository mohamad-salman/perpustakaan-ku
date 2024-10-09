package ms.perpusku.dao;

import java.util.List;
import java.util.Optional;
import ms.perpusku.domain.model.Penerbit;

/**
 *
 * @author MS
 */
public interface PenerbitDAO {
    boolean insert(Penerbit penerbit);
    List<Penerbit> get();
    Optional<Penerbit> get(long id);
    
}
