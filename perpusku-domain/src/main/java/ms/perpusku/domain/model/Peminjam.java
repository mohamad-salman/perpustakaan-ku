package ms.perpusku.domain.model;

/**
 *
 * @author MS
 */
public class Peminjam {

    private final long id;
    private final String nama;
    private final String telp;

    public Peminjam(String nama, String telp) {
        id = 0;
        this.nama = nama;
        this.telp = telp;
    }

    public Peminjam(long id, String nama, String telp) {
        this.id = id;
        this.nama = nama;
        this.telp = telp;
    }

    public long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTelp() {
        return telp;
    }

    @Override
    public String toString() {
        return nama + "(" + id + ") -- " + telp;
    }

}
