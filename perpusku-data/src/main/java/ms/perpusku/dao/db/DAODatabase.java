package ms.perpusku.dao.db;

import java.sql.Connection;

/**
 *
 * @author MS
 */
abstract class DAODatabase {
    protected final Connection connection;

    public DAODatabase(Connection connection) {
        this.connection = connection;
    }
    
}
