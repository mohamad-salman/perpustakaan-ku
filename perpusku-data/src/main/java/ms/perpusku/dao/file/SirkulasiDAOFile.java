package ms.perpusku.dao.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ms.perpusku.dao.BukuDAO;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.dao.PeminjamDAO;
import ms.perpusku.dao.SirkulasiDAO;
import ms.perpusku.dao.file.converter.SirkulasiConverter;
import ms.perpusku.domain.model.Sirkulasi;

/**
 *
 * @author MS
 */
public class SirkulasiDAOFile implements SirkulasiDAO {

    private final BukuDAO bukuDAO;
    private final PeminjamDAO peminjamDAO;

    private static final String FILE_SIRKULASI = "sirkulasi.csv";
    private static final String DATE_PATTERN = "dd-MM-yyyy";

    public SirkulasiDAOFile() {
        DAOFactory daoFactory = DAOFactory.getInstance();

        bukuDAO = daoFactory.getBukuDAO();
        peminjamDAO = daoFactory.getPeminjamDAO();
    }

    @Override
    public boolean insert(List<Sirkulasi.Rincian> listRincianSirkulasi) {
        listRincianSirkulasi.forEach(rincianSirkulasi -> {
            String csv = new SirkulasiConverter().domainObjectToCSV(rincianSirkulasi);

            DAOFile.insertDataToFile(csv, FILE_SIRKULASI);
        });

        return true;
    }

    @Override
    public Sirkulasi get() {
        Sirkulasi sirkulasi = new Sirkulasi();

        List<Sirkulasi.Rincian> list = DAOFile.getDataFromFile(FILE_SIRKULASI, new SirkulasiConverter());
        list.forEach(rincian -> sirkulasi.add(rincian));

        return sirkulasi;
    }

    @Override
    public boolean update(Sirkulasi sirkulasi) {
        try {

            Files.writeString(
                    Paths.get("../resources/csv/" + FILE_SIRKULASI),
                    toCSV(sirkulasi),
                    StandardOpenOption.TRUNCATE_EXISTING
            );
            
        } catch (IOException ex) {
            Logger.getLogger(SirkulasiDAOFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    private String toCSV(Sirkulasi sirkulasi) {
        StringBuilder sb = new StringBuilder();

        sirkulasi.get()
                .forEach(rincianSirkulasi -> {
                    String idBuku = getIdBuku(rincianSirkulasi);
                    String idPeminjam = getIdPeminjam(rincianSirkulasi);
                    String diPinjam = getDiPinjam(rincianSirkulasi);
                    String diKembalikan = getDiKembalikan(rincianSirkulasi);

                    StringJoiner joinerKoma = new StringJoiner(",");
                    joinerKoma
                            .add(idBuku)
                            .add(idPeminjam)
                            .add(diPinjam);

                    if (!diKembalikan.isEmpty()) {
                        joinerKoma.add(diKembalikan);
                    }

                    sb.append(joinerKoma.toString()).append("\n");
                });

        return sb.toString();
    }

    private String getIdBuku(Sirkulasi.Rincian rincianSirkulasi) {
        return String.valueOf(rincianSirkulasi.getBuku().getId());
    }

    private String getIdPeminjam(Sirkulasi.Rincian rincianSirkulasi) {
        return String.valueOf(rincianSirkulasi.getPeminjam().getId());
    }

    private String getDiPinjam(Sirkulasi.Rincian rincianSirkulasi) {
        return formatTanggal(rincianSirkulasi.getDiPinjam());

    }

    private String getDiKembalikan(Sirkulasi.Rincian rincianSirkulasi) {
        return rincianSirkulasi.getDiKembalikan() == null ? ""
                : formatTanggal(rincianSirkulasi.getDiKembalikan());

    }

    private String formatTanggal(LocalDate tanggal) {
        return tanggal.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
