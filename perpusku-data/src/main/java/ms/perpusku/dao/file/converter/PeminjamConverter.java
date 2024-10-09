package ms.perpusku.dao.file.converter;

import ms.perpusku.domain.model.Peminjam;

/**
 *
 * @author MS
 */
public class PeminjamConverter implements CSVConverter<Peminjam> {

    @Override
    public Peminjam csvToDomainObject(String record) {
        String[] recordPartArr = record.split(","); // [0]id, [1]nama, [2]telp

        long id = getId(recordPartArr);
        String nama = getNama(recordPartArr);
        String telp = getTelp(recordPartArr);

        return new Peminjam(id, nama, telp);
    }

    public long getId(String[] recordPartArr) {
        final int INDEX_COLUMN_ID = 0;
        long id = Long.valueOf(recordPartArr[INDEX_COLUMN_ID]);
        
        return id;
    }

    public String getNama(String[] recordPartArr) {
        final int INDEX_COLUMN_NAMA = 1;
        String nama = recordPartArr[INDEX_COLUMN_NAMA];
        
        return nama;
    }

    public String getTelp(String[] recordPartArr) {
        final int INDEX_COLUMN_TELP = 2;
        String telp = recordPartArr[INDEX_COLUMN_TELP];
        
        return telp;
    }

    @Override
    public String domainObjectToCSV(Peminjam peminjam) {
        return String.join(",",
                String.valueOf(peminjam.getId()),
                peminjam.getNama(),
                peminjam.getTelp()
        );
    }

}
