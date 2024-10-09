package ms.perpusku.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author MS
 */
public class Sirkulasi {

    private final List<Rincian> listRincian;

    public Sirkulasi() {
        listRincian = new ArrayList<>();
    }

    public void add(Rincian rincian) {
        if (rincian != null) {
            listRincian.add(rincian);
        }
    }

    public void peminjaman(Buku buku, Peminjam peminjam, LocalDate diPinjam) {
        if (buku != null && peminjam != null && diPinjam != null) {
            listRincian.add(new Rincian(buku, peminjam, diPinjam));
        }
    }

    public void pengembalian(Buku buku) {
        if (buku != null) {
            Optional<Sirkulasi.Rincian> rincianSirkulasi = getBukuDiPinjam()
                    .filter(rincian -> rincian.getBuku().equals(buku))
                    .findFirst();

            rincianSirkulasi.ifPresent(rs -> rs.setDiKembalikan(LocalDate.now()));
        }
    }

    public Stream<Sirkulasi.Rincian> getBukuDiPinjam() {
        return listRincian.stream()
                .filter(rincian -> rincian.getDiKembalikan() == null);
    }

    public Stream<Sirkulasi.Rincian> get() {
        return listRincian.stream();
    }

    public void display() {
        if (!listRincian.isEmpty()) {
            listRincian.forEach(System.out::println);
        }
    }

    public static class Rincian {

        private final Buku buku;
        private final Peminjam peminjam;
        private final LocalDate diPinjam;
        private LocalDate diKembalikan;

        public Rincian(Buku buku, Peminjam peminjam, LocalDate diPinjam) {
            this.buku = buku;
            this.peminjam = peminjam;
            this.diPinjam = diPinjam;
        }

        public Buku getBuku() {
            return buku;
        }

        public Peminjam getPeminjam() {
            return peminjam;
        }

        public LocalDate getDiPinjam() {
            return diPinjam;
        }

        public LocalDate getDiKembalikan() {
            return diKembalikan;
        }

        public void setDiKembalikan(LocalDate diKembalikan) {
            this.diKembalikan = diKembalikan;
        }

        @Override
        public String toString() {
            String dataPattern = "dd-MM-yyyy";

            return String.format("%-4s %-4s %-8s %s",
                    buku.getId(),
                    peminjam.getId(),
                    diPinjam.format(DateTimeFormatter.ofPattern(dataPattern)),
                    diKembalikan == null
                            ? "   ----   "
                            : diKembalikan.format(DateTimeFormatter.ofPattern(dataPattern))
            );
        }

    }
}
