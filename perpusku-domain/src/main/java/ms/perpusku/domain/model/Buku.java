package ms.perpusku.domain.model;

import java.time.Year;
import java.util.List;

/**
 *
 * @author MS
 */
public class Buku {

    private final long id;
    private final String judul;
    private final List<Pengarang> listPengarang;
    private final Penerbit penerbit;
    private final Year tahunTerbit;
    private final Kategori kategori;
    private final List<String> listIsbn;

    private Buku(Builder builder) {
        this.id = builder.id;
        this.judul = builder.judul;
        this.penerbit = builder.penerbit;
        this.tahunTerbit = builder.tahunTerbit;
        this.kategori = builder.kategori;
        this.listPengarang = builder.listPengarang;
        this.listIsbn = builder.listIsbn;
    }

    public long getId() {
        return id;
    }

    public List<Pengarang> getPengarang() {
        return listPengarang;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public List<String> getIsbn() {
        return listIsbn;
    }

    public String getJudul() {
        return judul;
    }

    public Penerbit getPenerbit() {
        return penerbit;
    }

    public Year getTahunTerbit() {
        return tahunTerbit;
    }

    public void addIsbn(String isbn) {
        this.listIsbn.add(isbn);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Buku other = (Buku) obj;
        return this.id == other.id;
    }

    public void display() {
        System.out.println("");
        System.out.println("id       : " + id);
        System.out.println("judul    : " + judul);

        System.out.print("pengarang: " + "[");
        int totalPengarang = listPengarang.size();
        for (int i = 0; i < totalPengarang; i++) {
            System.out.print(listPengarang.get(i));

            if (i != (totalPengarang - 1)) {
                System.out.print(", ");
            }

        }
        System.out.println("]");

        System.out.println("penerbit : " + getPenerbit());
        System.out.println("tahun    : " + getTahunTerbit().getValue());
        System.out.println("kategori : " + getKategori());

        System.out.print("isbn     : [");
        int totalIsbn = listIsbn.size();
        for (int i = 0; i < totalIsbn; i++) {
            System.out.print(listIsbn.get(i));

            if (i != (totalIsbn - 1)) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    @Override
    public String toString() {
        return judul + "\t" + listPengarang + "\t" + penerbit + "\t" + tahunTerbit
                + "\t" + kategori + "\t" + listIsbn;
    }

    public static class Kategori {

        private final String kode;
        private final String kategori;

        public Kategori(String kode, String kategori) {
            this.kode = kode;
            this.kategori = kategori;
        }

        public String getKode() {
            return kode;
        }

        public String getNama() {
            return kategori;
        }

        @Override
        public String toString() {
            return kategori + " (" + kode + ")";
        }
    }

    public static class Builder {

        private long id;
        private final String judul;
        private final List<Pengarang> listPengarang;
        private Penerbit penerbit;
        private Year tahunTerbit;
        private Kategori kategori;
        private List<String> listIsbn;

        public Builder(String judul, List<Pengarang> listPengarang) {
            this.judul = judul;
            this.listPengarang = listPengarang;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder penerbit(Penerbit penerbit) {
            this.penerbit = penerbit;
            return this;
        }

        public Builder tahun(Year tahun) {
            this.tahunTerbit = tahun;
            return this;
        }

        public Builder kategori(Kategori kategori) {
            this.kategori = kategori;
            return this;
        }

        public Builder isbn(List<String> listIsbn) {
            this.listIsbn = listIsbn;
            return this;
        }

        public Buku build() {
            return new Buku(this);
        }
    }
}
