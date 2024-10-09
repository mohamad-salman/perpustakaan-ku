package ms.perpusku.dao;

import ms.perpusku.dao.file.BukuDAOFile;
import ms.perpusku.dao.file.PenerbitDAOFile;
import ms.perpusku.dao.file.PeminjamDAOFile;
import ms.perpusku.dao.file.PengarangDAOFile;
import ms.perpusku.dao.file.SirkulasiDAOFile;

/**
 *
 * @author MS
 */
public class DAOFactory {

//    private static Connection connection;
    private static DAOFactory daoFactory;

    private static BukuDAO bukuDAO;
    private static PengarangDAO pengarangDAO;
    private static PenerbitDAO penerbitDAO;
    private static PeminjamDAO peminjamDAO;
    private static SirkulasiDAO sirkulasiDAO;

    private DAOFactory() {
//        connection = DatabasePerpusku.getConnection();
        daoFactory = null;

        bukuDAO = null;
        pengarangDAO = null;
        penerbitDAO = null;
        peminjamDAO = null;
        sirkulasiDAO = null;
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public BukuDAO getBukuDAO() {
        if (bukuDAO == null) {
            bukuDAO = new BukuDAOFile();
        }
        return bukuDAO;
    }

    public PengarangDAO getPengarangDAO() {
        if (pengarangDAO == null) {
            pengarangDAO = new PengarangDAOFile();
//            pengarangDAO = new PengarangDAODatabase(connection);
        }
        return pengarangDAO;
    }

    public PenerbitDAO getPenerbitDAO() {
        if (penerbitDAO == null) {
            penerbitDAO = new PenerbitDAOFile();
        }
        return penerbitDAO;
    }

    public PeminjamDAO getPeminjamDAO() {
        if (peminjamDAO == null) {
            peminjamDAO = new PeminjamDAOFile();
        }
        return peminjamDAO;
    }

    public SirkulasiDAO getSirkulasiDAO() {
        if (sirkulasiDAO == null) {
            sirkulasiDAO = new SirkulasiDAOFile();
        }
        return sirkulasiDAO;
    }
}
