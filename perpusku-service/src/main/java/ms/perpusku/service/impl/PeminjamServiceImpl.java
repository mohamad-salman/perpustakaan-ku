package ms.perpusku.service.impl;

import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.domain.model.Peminjam;
import ms.perpusku.dao.PeminjamDAO;
import ms.perpusku.service.api.PeminjamService;

/**
 *
 * @author MS
 */
public class PeminjamServiceImpl implements PeminjamService {

    private final PeminjamDAO peminjamDAO;

    public PeminjamServiceImpl() {
        this.peminjamDAO = DAOFactory.getInstance().getPeminjamDAO();
    }

    @Override
    public List<Peminjam> get() {
        return peminjamDAO.get();
    }

    @Override
    public Optional<Peminjam> get(long id) {
        return peminjamDAO.get(id);
    }

    @Override
    public boolean simpanPeminjam(String nama, String telp) {
        return peminjamDAO.insert(new Peminjam(nama, telp));
    }

}
