package ms.perpusku.cli.input;

import ms.perpusku.cli.shared.StringConvert;
import java.time.Year;
import java.util.Set;
import java.util.function.Predicate;
import ms.perpusku.cli.input.validator.KondisiCekInputBuilder;
import ms.perpusku.cli.input.validator.DefaultValidator;

/**
 *
 * @author MS
 */
public class TerminalInputTambahBuku implements TerminalInput {

    public TerminalInputTambahBuku() {
    }

    public String getJudul() {
        DefaultValidator validator = KondisiCekInputBuilder.create()
                .harusDiisi("Judul")
                .ituSaja();

        String pesanInput = "Masukkan judul: ";

        return get(validator, pesanInput);
    }

    public int getJumlahPengarang() {
        DefaultValidator validator = buatValidatorJumlahPengarang();

        String pesanInput = "Masukkan jumlah pengarang (maksimal sepuluh): ";

        return StringConvert.toInt(get(validator, pesanInput));
    }

    public DefaultValidator buatValidatorJumlahPengarang() {
        final String JUMLAH = "Jumlah";
        final long MAKSIMAL_JUMLAH_PENGARANG = 10L;

        return KondisiCekInputBuilder.create()
                .harusDiisi(JUMLAH)
                .hanyaMenerimaAngkaPositif()
                .maksimal(JUMLAH, MAKSIMAL_JUMLAH_PENGARANG)
                .ituSaja();
    }

    public long getNoUrutPengarang(int index, long totalPengarang, Set<Long> noUrutSudahDiPilih) {
        DefaultValidator validator = buatValidatorNoUrutPengarang(totalPengarang, noUrutSudahDiPilih);

        int pengarangKe = index + 1;
        String pesanInput = "Pilih pengarang ke-" + pengarangKe + ": ";

        return StringConvert.toLong(get(validator, pesanInput));
    }

    public DefaultValidator buatValidatorNoUrutPengarang(long totalPengarang, Set<Long> noUrutSudahDiPilih) {
        final String PILIHAN_NO = "Pilihan no";

        return KondisiCekInputBuilder.create()
                .harusDiisi(PILIHAN_NO)
                .hanyaMenerimaAngkaPositif()
                .maksimal(PILIHAN_NO, totalPengarang)
                .tidakBerulang(PILIHAN_NO, noUrutSudahDiPilih)
                .ituSaja();
    }

    public int getNoPenerbit(long totalPenerbit) {
        DefaultValidator validator = buatValidatorNoPenerbit(totalPenerbit);

        String pesanInput = "Pilih penerbit: ";

        return StringConvert.toInt(get(validator, pesanInput));
    }

    public DefaultValidator buatValidatorNoPenerbit(long totalPenerbit) {
        final String PILIHAN_NO = "Pilihan no";

        return KondisiCekInputBuilder.create()
                .harusDiisi(PILIHAN_NO)
                .hanyaMenerimaAngkaPositif()
                .maksimal(PILIHAN_NO, totalPenerbit)
                .ituSaja();
    }

    public int getTahunTerbit() {
        DefaultValidator validator = buatValidatorTahunTerbit();

        String pesanInput = "\nMasukkan tahun (antara 1000 - tahun saat ini): ";

        return StringConvert.toInt(get(validator, pesanInput));
    }

    public DefaultValidator buatValidatorTahunTerbit() {
        Predicate<String> antaraTahun = str -> {
            int inputTahun = Integer.valueOf(str);
            int tahunSaatIni = Year.now().getValue();

            return inputTahun <= 1000 || inputTahun > tahunSaatIni;
        };

        String pesanError = "Tahun yang dimasukkan tidak valid";

        return KondisiCekInputBuilder.create()
                .harusDiisi("Tahun")
                .hanyaMenerimaAngkaNolKeAtas()
                .dan(antaraTahun, pesanError)
                .ituSaja();
    }

    public String getKategori(long totalKategori) {
        DefaultValidator validator = buatValidatorKategori(totalKategori);

        String pesanInput = "Pilih kategori: ";

        return get(validator, pesanInput);
    }

    public DefaultValidator buatValidatorKategori(long totalKategori) {
        final String PILIHAN_NO = "Pilihan no";

        return KondisiCekInputBuilder.create()
                .harusDiisi(PILIHAN_NO)
                .hanyaMenerimaAngkaPositif()
                .maksimal(PILIHAN_NO, totalKategori)
                .ituSaja();
    }

    public int getJumlahIsbn() {
        DefaultValidator validator = buatValidatorJumlahIsbn();

        String pesanInput = "\nMasukkan jumlah no isbn (maksimal dua, angka nol bila tidak ada): ";

        return StringConvert.toInt(get(validator, pesanInput));
    }

    public DefaultValidator buatValidatorJumlahIsbn() {
        final String JUMLAH = "Jumlah";
        final long JUMLAH_MAKSIMAL_NO_ISBN = 2L;

        return KondisiCekInputBuilder.create()
                .harusDiisi(JUMLAH)
                .hanyaMenerimaAngkaNolKeAtas()
                .maksimal(JUMLAH, JUMLAH_MAKSIMAL_NO_ISBN)
                .ituSaja();
    }

    public String getNoIsbn(int index, Set<String> noIsbnYangSudahTerdaftar) {
        DefaultValidator validator = buatValidatorNoIsbn(noIsbnYangSudahTerdaftar);

        int isbnKe = index + 1;
        String pesanInput = "Masukkan no isbn ke-" + isbnKe + ": ";

        return get(validator, pesanInput);
    }

    public DefaultValidator buatValidatorNoIsbn(Set<String> noIsbnYangSudahTerdaftar) {
        final String NO = "No";
        Predicate<String> hanyaAngkaDanMinus = str -> !str.matches("[0-9-]+");
        String pesanError = "Hanya menerima angka nol ke atas dan karakter minus (-)";

        return KondisiCekInputBuilder.create()
                .harusDiisi(NO)
                .dan(hanyaAngkaDanMinus, pesanError)
                .sudahTerdaftar(NO, noIsbnYangSudahTerdaftar)
                .ituSaja();
    }

}
