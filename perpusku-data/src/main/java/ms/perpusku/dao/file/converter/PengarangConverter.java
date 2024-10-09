package ms.perpusku.dao.file.converter;

import ms.perpusku.domain.model.Pengarang;

/**
 *
 * @author MS
 */
public class PengarangConverter implements CSVConverter<Pengarang> {

    @Override
    public Pengarang csvToDomainObject(String record) {
        String[] recordPartArr = record.split(","); // [0]id, [1]nama

        long id = getId(recordPartArr);
        String nama = getNama(recordPartArr);

        return new Pengarang(id, nama);
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

    @Override
    public String domainObjectToCSV(Pengarang pengarang) {
        return String.join(",",
                String.valueOf(pengarang.getId()),
                pengarang.getNama()
        );
    }
}
