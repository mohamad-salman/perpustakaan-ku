package ms.perpusku.dao;

import java.util.List;
import ms.perpusku.domain.model.Sirkulasi;

/**
 *
 * @author MS
 */
public interface SirkulasiDAO {
    boolean insert(List<Sirkulasi.Rincian> listRincianSIrkulasi);
    boolean update(Sirkulasi sirkulasi);
    Sirkulasi get();
}
