package ms.perpusku.service.impl;

import ms.perpusku.service.api.BukuService;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import ms.perpusku.dao.BukuDAO;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Penerbit;
import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author Salman
 */
public class BukuServiceImpl implements BukuService {

    private final BukuDAO bukuDAO;

    public BukuServiceImpl() {
        this.bukuDAO = DAOFactory.getInstance().getBukuDAO();
    }

    @Override
    public int getTotalKategori() {
        return getAllKategori().size();
    }

    @Override
    public List<Buku.Kategori> getAllKategori() {
        return bukuDAO.getAllKategori();
    }

    @Override
    public Optional<Buku.Kategori> getKategori(String kode) {
        return bukuDAO.getKategori(kode);
    }

    @Override
    public List<Buku> get() {
        return bukuDAO.get();
    }

    @Override
    public Optional<Buku> get(long id) {
        return bukuDAO.get(id);
    }

    @Override
    public boolean simpanBuku(String judul, List<Pengarang> listPengarang,
            Penerbit penerbit, Year tahun, String kodeKategori, List<String> listIsbn) {

        Optional<Buku.Kategori> kategori = getKategori(kodeKategori);

        Buku buku = new Buku.Builder(judul, listPengarang)
                .penerbit(penerbit)
                .tahun(tahun)
                .kategori(kategori.get())
                .isbn(listIsbn)
                .build();

        bukuDAO.insert(buku);

        return true;
    }

}
