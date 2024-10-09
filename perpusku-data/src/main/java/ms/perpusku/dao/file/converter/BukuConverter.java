package ms.perpusku.dao.file.converter;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import ms.perpusku.dao.BukuDAO;
import ms.perpusku.dao.DAOFactory;
import ms.perpusku.dao.PenerbitDAO;
import ms.perpusku.dao.PengarangDAO;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Penerbit;
import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author MS
 */
public class BukuConverter implements CSVConverter<Buku> {

    private final BukuDAO bukuDAO;
    private final PengarangDAO pengarangDAO;
    private final PenerbitDAO penerbitDAO;

    public BukuConverter() {
        DAOFactory daoFactory = DAOFactory.getInstance();

        bukuDAO = daoFactory.getBukuDAO();
        pengarangDAO = daoFactory.getPengarangDAO();
        penerbitDAO = daoFactory.getPenerbitDAO();
    }

    @Override
    public Buku csvToDomainObject(String record) { 
        String[] recordPartArr = record.split("#"); // // id_buku, id_penerbit, tahun, kode_kategori# judul# id_pengarang# no_isbn

        String[] recordSubPartSatuArr = recordPartArr[0].split(","); // [0]id_buku, [1]id_penerbit, [2]tahun, [3]kode_kategori
        String recordSubPartDua = recordPartArr[1]; // judul
        String recordSubPartTiga = recordPartArr[2]; // id_pengarang

        long idBuku = getIdBuku(recordSubPartSatuArr);
        Penerbit penerbit = getPenerbit(recordSubPartSatuArr);
        Year tahun = getTahun(recordSubPartSatuArr);
        Buku.Kategori kategori = getKategori(recordSubPartSatuArr);

        String judul = getJudul(recordSubPartDua);

        List<Pengarang> listPengarang = getListPengarang(recordSubPartTiga);

        List<String> listIsbn = getNoIsbn(recordPartArr);

        return new Buku.Builder(judul, listPengarang)
                .id(idBuku)
                .penerbit(penerbit)
                .tahun(tahun)
                .kategori(kategori)
                .isbn(listIsbn)
                .build();
    }

    private long getIdBuku(String[] recordSubPartSatuArr) {
        final int INDEX_COLUMN_ID = 0;

        return Long.valueOf(recordSubPartSatuArr[INDEX_COLUMN_ID]);
    }

    private String getJudul(String recordSubPartDua) {
        return recordSubPartDua;
    }

    private Penerbit getPenerbit(String[] recordSubPartSatuArr) {
        final int INDEX_COLUMN_ID_PENERBIT = 1;
        long idPenerbit = Long.valueOf(recordSubPartSatuArr[INDEX_COLUMN_ID_PENERBIT]);

        return penerbitDAO.get(idPenerbit).get();
    }

    private Year getTahun(String[] recordSubPartSatuArr) {
        final int INDEX_COLUMN_TAHUN = 2;
        Year tahun = Year.of(Integer.valueOf(recordSubPartSatuArr[INDEX_COLUMN_TAHUN]));

        return tahun;
    }

    private Buku.Kategori getKategori(String[] recordSubPartSatuArr) {
        final int INDEX_COLUMN_ID_KATEGORI = 3;
        String kodeKategori = recordSubPartSatuArr[INDEX_COLUMN_ID_KATEGORI];

        return bukuDAO.getKategori(kodeKategori).get();
    }

    private List<Pengarang> getListPengarang(String recordSubPartTiga) {
        String[] recordIdPengarangPart = getRecordPart(recordSubPartTiga);

        List<Pengarang> listPengarang = new ArrayList<>();
        for (String idStr : recordIdPengarangPart) {
            long id = Integer.valueOf(idStr);
            listPengarang.add(pengarangDAO.get(id).get());
        }

        return listPengarang;
    }

    private List<String> getNoIsbn(String[] recordPartArr) {
        List<String> list = new ArrayList<>();
        if (recordPartArr.length > 3) {
            final int INDEX_COLUMN_NO_ISBN = 3;
            String[] recordNoIsbnPart = getRecordPart(recordPartArr[INDEX_COLUMN_NO_ISBN]);

            list = Arrays.asList(recordNoIsbnPart);
        }
        return list;
    }

    @Override
    public String domainObjectToCSV(Buku buku) {
        StringJoiner joinerKoma = new StringJoiner(",");
        StringJoiner joinerPagar = new StringJoiner("#");

        joinerKoma
                .add(String.valueOf(buku.getId()))
                .add(String.valueOf(buku.getPenerbit().getId()))
                .add(String.valueOf(buku.getTahunTerbit().getValue()))
                .add(buku.getKategori().getKode());

        joinerPagar
                .add(joinerKoma.toString())
                .add(buku.getJudul())
                .add(listPengarang(buku.getPengarang()));

        List<String> noIsbn = buku.getIsbn();
        if (!noIsbn.isEmpty()) {
            joinerPagar.add(String.join(",", noIsbn));
        }

        return joinerPagar.toString();
    }

    private String listPengarang(List<Pengarang> list) {
        return list.stream()
                .map(pengarang -> String.valueOf(pengarang.getId()))
                .collect(Collectors.joining(","));

    }

}
