package ms.perpusku.service.impl;

import ms.perpusku.service.api.PengarangService;
import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.dao.PengarangDAO;
import ms.perpusku.domain.model.Pengarang;

/**
 * I
 *
 * @author MS
 */
public class PengarangServiceImpl implements PengarangService {

    private final PengarangDAO pengarangDAO;

    public PengarangServiceImpl() {
        this.pengarangDAO = DAOFactory.getInstance().getPengarangDAO();
    }

    @Override
    public Optional<Pengarang> get(long id) {
        return pengarangDAO.get(id);
    }

    @Override
    public List<Pengarang> getAll() {
        return pengarangDAO.get();
    }

    @Override
    public long getTotalPengarang() {
        return getAll().size();
    }

}
