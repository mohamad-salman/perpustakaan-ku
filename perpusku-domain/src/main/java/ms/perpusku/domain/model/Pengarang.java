package ms.perpusku.domain.model;

/**
 *
 * @author MS
 */
public class Pengarang {

    private final long id;
    private final String nama;

    public Pengarang(long id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return nama + " (" + id + ")";
    }

}
