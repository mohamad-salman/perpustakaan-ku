package ms.perpusku.cli.input;

import ms.perpusku.cli.shared.StringConvert;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import ms.perpusku.cli.input.validator.KondisiCekInputBuilder;
import ms.perpusku.cli.input.validator.DefaultValidator;
import ms.perpusku.cli.shared.Konfirmasi;
import static ms.perpusku.cli.shared.Konfirmasi.TIDAK;
import static ms.perpusku.cli.shared.Konfirmasi.YA;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Peminjam;
import ms.perpusku.service.api.BukuService;
import ms.perpusku.service.api.PeminjamService;
import ms.perpusku.service.api.SirkulasiService;

/**
 *
 * @author MS
 */
public class TerminalInputPeminjaman implements TerminalInput {

    public TerminalInputPeminjaman() {
    }

    public long getIdPeminjam(PeminjamService peminjamService) {
        DefaultValidator validator = buatValidatorIdPeminjam(peminjamService);
        String pesanInput = "\nMasukkan ID peminjam: ";

        return StringConvert.toLong(get(validator, pesanInput));
    }

    private DefaultValidator buatValidatorIdPeminjam(PeminjamService peminjamService) {
        Predicate<String> idTidakTerdaftar = in -> {
            long id = StringConvert.toLong(in);
            Optional<Peminjam> peminjam = peminjamService.get(id);

            return !peminjam.isPresent();
        };

        return KondisiCekInputBuilder.create()
                .harusDiisi("ID")
                .hanyaMenerimaAngkaPositif()
                .dan(idTidakTerdaftar, "ID peminjam tidak terdaftar")
                .ituSaja();
    }

    public Konfirmasi konfirmasiPeminjam(long id, PeminjamService peminjamService) {
        Optional<Peminjam> peminjam = peminjamService.get(id);

        return konfirmasi("peminjamnya", peminjam.get().getNama());
    }

    private Konfirmasi konfirmasi(String subyek, String nilaiSubyek) {
        Predicate<String> yaTidak = in -> !in.equalsIgnoreCase("y") || !in.equalsIgnoreCase("t");

        String pesanInput = "Apakah betul " + subyek + " \"" + nilaiSubyek + "\" ? [y/t] ";
        String in = get(yaTidak, pesanInput, "");

        return in.equals(YA.value()) ? YA : TIDAK;
    }

    public int getJumlahBukuYangDipinjam() {
        DefaultValidator validator = buatValidatorJumlahBukuYangDiPinjam();
        String pesanInput = "\nMasukkan jumlah buku yang dipinjam (maksimal dua): ";

        return StringConvert.toInt(get(validator, pesanInput));
    }

    private DefaultValidator buatValidatorJumlahBukuYangDiPinjam() {
        final long JUMLAH_MAKSIMAL_PEMINJAMAN = 2L;

        return KondisiCekInputBuilder.create()
                .harusDiisi("Jumlah")
                .hanyaMenerimaAngkaPositif()
                .maksimal("Jumlah", JUMLAH_MAKSIMAL_PEMINJAMAN)
                .ituSaja();
    }

    public long getIdBuku(int index, BukuService bukuService, Set<Long> idBukuSudahDiPilih,
            SirkulasiService sirkulasiService) {

        DefaultValidator validator = buatValidatorIdBuku(bukuService, idBukuSudahDiPilih, sirkulasiService);

        int bukuKe = index + 1;
        String pesanInput = "\nMasukkan ID buku ke-" + bukuKe + ": ";

        return StringConvert.toLong(get(validator, pesanInput));
    }

    private DefaultValidator buatValidatorIdBuku(BukuService bukuService, Set<Long> idBukuSudahDiPilih,
            SirkulasiService sirkulasiService) {

        Predicate<String> idTidakTerdaftar = in -> {
            long id = StringConvert.toLong(in);
            Optional<Buku> buku = bukuService.get(id);

            return !buku.isPresent();
        };

        Predicate<String> sedangDiPinjam = in -> {
            long id = StringConvert.toLong(in);
            Buku buku = bukuService.get(id).get();

            return sirkulasiService.isBukuDiPinjam(buku);
        };

        return KondisiCekInputBuilder.create()
                .harusDiisi("ID")
                .hanyaMenerimaAngkaPositif()
                .dan(idTidakTerdaftar, "ID buku tidak terdaftar")
                .dan(sedangDiPinjam, "Buku sedang dipinjam")
                .tidakBerulang("ID", idBukuSudahDiPilih)
                .ituSaja();
    }

    public Konfirmasi konfirmasiBuku(long idBuku, BukuService bukuService) {
        Optional<Buku> buku = bukuService.get(idBuku);

        return konfirmasi("bukunya", buku.get().getJudul());
    }
}
