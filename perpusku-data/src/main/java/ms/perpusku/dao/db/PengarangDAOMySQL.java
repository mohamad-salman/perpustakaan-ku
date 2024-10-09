package ms.perpusku.dao.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.PengarangDAO;
import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author MS
 */
public class PengarangDAOMySQL extends DAODatabase implements PengarangDAO {

    public PengarangDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public List<Pengarang> get() {
        return new ArrayList<>();
    }

    @Override
    public Optional<Pengarang> get(long id) {
        return Optional.empty();
    }

    @Override
    public boolean insert(Pengarang pengarang) {
        System.out.println("Not supported yet.");
        return false;
    }
    
}
