package ms.perpusku.dao.file.converter;

import ms.perpusku.domain.model.Buku;

/**
 *
 * @author MS
 */
public class KategoriConverter implements CSVConverter<Buku.Kategori> {

    @Override
    public Buku.Kategori csvToDomainObject(String record) {
        String[] recordPartArr = record.split(","); // [0]kode, [1]nama

        String kode = getKode(recordPartArr);
        String kategori = getNama(recordPartArr);

        return new Buku.Kategori(kode, kategori);
    }

    public String getKode(String[] recordPartArr) {
        final int INDEX_COLUMN_KODE = 0;
        String kode = recordPartArr[INDEX_COLUMN_KODE];

        return kode;
    }

    public String getNama(String[] recordPartArr) {
        final int INDEX_COLUMN_KATEGORI = 1;
        String kategori = recordPartArr[INDEX_COLUMN_KATEGORI];
        
        return kategori;
    }

    @Override
    public String domainObjectToCSV(Buku.Kategori kategori) {
        return String.join(",",
                kategori.getKode(),
                kategori.getNama()
        );
    }

}
