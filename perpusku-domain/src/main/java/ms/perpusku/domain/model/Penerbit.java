package ms.perpusku.domain.model;

/**
 *
 * @author MS
 */
public class Penerbit {

    private final long id;
    private final String nama;

    public Penerbit(long id, String nama) {
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
