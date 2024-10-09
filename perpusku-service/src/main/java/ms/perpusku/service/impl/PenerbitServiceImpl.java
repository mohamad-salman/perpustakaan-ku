package ms.perpusku.service.impl;

import ms.perpusku.service.api.PenerbitService;
import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.dao.PenerbitDAO;
import ms.perpusku.domain.model.Penerbit;

/**
 *
 * @author MS
 */
public class PenerbitServiceImpl implements PenerbitService {

    private final PenerbitDAO penerbitDAO;

    public PenerbitServiceImpl() {
        this.penerbitDAO = DAOFactory.getInstance().getPenerbitDAO();
    }

    @Override
    public List<Penerbit> getAll() {
        return penerbitDAO.get();
    }

    @Override
    public long getTotalPenerbit() {
        return getAll().size();
    }

    @Override
    public Optional<Penerbit> get(long id) {
        return penerbitDAO.get(id);
    }

}
