package ms.perpusku.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.dao.PeminjamDAO;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Peminjam;
import ms.perpusku.dao.SirkulasiDAO;
import ms.perpusku.domain.model.Sirkulasi;
import ms.perpusku.service.api.SirkulasiService;

/**
 *
 * @author MS
 */
public class SirkulasiServiceImpl implements SirkulasiService {

    private final PeminjamDAO peminjamDAO;
    private final SirkulasiDAO sirkulasiDAO;

    public SirkulasiServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();

        peminjamDAO = daoFactory.getPeminjamDAO();
        sirkulasiDAO = daoFactory.getSirkulasiDAO();
    }

    @Override
    public boolean simpanPeminjaman(long idPeminjam, List<Buku> listBuku) {
        LocalDate tanggal = LocalDate.now();
        Peminjam peminjam = peminjamDAO.get(idPeminjam).get();

        List<Sirkulasi.Rincian> listRincianSirkulasi = listBuku.stream()
                .map(buku -> new Sirkulasi.Rincian(buku, peminjam, tanggal))
                .collect(Collectors.toList());

        sirkulasiDAO.insert(listRincianSirkulasi);

        return true;
    }

    @Override
    public boolean simpanPengembalian(List<Buku> listBuku) {
        Sirkulasi sirkulasi = sirkulasiDAO.get();
        listBuku.forEach(buku -> sirkulasi.pengembalian(buku));

        sirkulasiDAO.update(sirkulasi);

        return true;
    }

    @Override
    public boolean isBukuDiPinjam(Buku buku) {
        return getBukuDiPinjam()
                .filter(rincianSirkulasi -> rincianSirkulasi.getBuku().equals(buku))
                .findAny()
                .isPresent();
    }

    @Override
    public long getTotalBukuDiPinjam() {
        return getBukuDiPinjam()
                .count();
    }

    @Override
    public Stream<Sirkulasi.Rincian> getBukuDiPinjam() {
        return sirkulasiDAO.get().getBukuDiPinjam();
    }

}
