package ms.perpusku.dao.file.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import ms.perpusku.dao.BukuDAO;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.dao.PeminjamDAO;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Peminjam;
import ms.perpusku.domain.model.Sirkulasi;

/**
 *
 * @author MS
 */
public class SirkulasiConverter implements CSVConverter<Sirkulasi.Rincian> {

    private final BukuDAO bukuDAO;
    private final PeminjamDAO peminjamDAO;

    private static final String DATE_PATTERN = "dd-MM-yyyy";

    public SirkulasiConverter() {
        DAOFactory daoFactory = DAOFactory.getInstance();

        bukuDAO = daoFactory.getBukuDAO();
        peminjamDAO = daoFactory.getPeminjamDAO();
    }

    @Override
    public Sirkulasi.Rincian csvToDomainObject(String record) {
        String[] recordPartArr = record.split(","); // [0]id_buku, [1]id_peminjam, [2] tgl_dipinjam, [3]tgl_dikembalikan

        Buku buku = getBuku(recordPartArr);
        Peminjam peminjam = getPeminjam(recordPartArr);
        LocalDate diPinjam = getDiPinjam(recordPartArr);
        LocalDate diKembalikan = getDiKembalikan(recordPartArr);

        Sirkulasi.Rincian rincianSirkulasi = new Sirkulasi.Rincian(buku, peminjam, diPinjam);

        if(diKembalikan != null) {
            rincianSirkulasi.setDiKembalikan(diKembalikan);
        }
        
        return rincianSirkulasi;
    }

    private Buku getBuku(String[] recordPartArr) {
        final int INDEX_COLUMN_ID_BUKU = 0;
        long id = Long.valueOf(recordPartArr[INDEX_COLUMN_ID_BUKU]);

        return bukuDAO.get(id).get();
    }

    private Peminjam getPeminjam(String[] recordPartArr) {
        final int INDEX_COLUMN_ID_PEMINJAM = 1;
        long id = Long.valueOf(recordPartArr[INDEX_COLUMN_ID_PEMINJAM]);

        return peminjamDAO.get(id).get();
    }

    private LocalDate getDiPinjam(String[] recordPartArr) {
        final int INDEX_COLUMN_DI_PINJAM = 2;
        String tgl = recordPartArr[INDEX_COLUMN_DI_PINJAM];

        return LocalDate.parse(tgl, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    private LocalDate getDiKembalikan(String[] recordPartArr) {
        if (recordPartArr.length > 3) {
            final int INDEX_COLUMN_DI_KEMBALIKAN = 3;
            String tgl = recordPartArr[INDEX_COLUMN_DI_KEMBALIKAN];
            
            return LocalDate.parse(tgl, DateTimeFormatter.ofPattern(DATE_PATTERN));
        }
        return null;
    }

    @Override
    public String domainObjectToCSV(Sirkulasi.Rincian rincianSirkulasi) {
        String idBuku = String.valueOf(rincianSirkulasi.getBuku().getId());
        String idPeminjam = String.valueOf(rincianSirkulasi.getPeminjam().getId());
        String tglDipinjam = rincianSirkulasi.getDiPinjam().format(DateTimeFormatter.ofPattern(DATE_PATTERN));

        StringJoiner joinerKoma = new StringJoiner(",")
                .add(idBuku)
                .add(idPeminjam)
                .add(tglDipinjam);

        if (rincianSirkulasi.getDiKembalikan() != null) {
            joinerKoma.add(rincianSirkulasi.getDiKembalikan().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        }

        return joinerKoma.toString();
    }

}
