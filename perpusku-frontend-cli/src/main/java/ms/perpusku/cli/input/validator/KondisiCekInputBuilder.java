package ms.perpusku.cli.input.validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 *
 * @author MS
 */
public class KondisiCekInputBuilder {

    List<Kondisi> listKondisi;

    private KondisiCekInputBuilder() {
        listKondisi = new LinkedList<>();
    }

    public static KondisiCekInputBuilder create() {
        return new KondisiCekInputBuilder();
    }

    public KondisiCekInputBuilder harusDiisi(String in) {
        Predicate<String> INPUT_TIDAK_KOSONG = str -> str.trim().isEmpty();
        String pesanError = !in.isEmpty() ? in + " harus diisi" : "";

        tambahKondisiKeList(INPUT_TIDAK_KOSONG, pesanError);
        return this;
    }

    public KondisiCekInputBuilder hanyaMenerimaHuruf() {
        Predicate<String> HANYA_HURUF = str -> !str.chars().allMatch(Character::isAlphabetic);
        String pesanError = "Hanya menerima masukkan huruf";

        tambahKondisiKeList(HANYA_HURUF, pesanError);
        return this;
    }

    public KondisiCekInputBuilder hanyaMenerimaAngkaNolKeAtas() {
        Predicate<String> HANYA_ANGKA_NOL_KE_ATAS = str -> !str.chars().allMatch(Character::isDigit);
        String pesanError = "Hanya menerima angka nol ke atas";

        tambahKondisiKeList(HANYA_ANGKA_NOL_KE_ATAS, pesanError);
        return this;
    }

    public KondisiCekInputBuilder hanyaMenerimaAngkaPositif() {
        Predicate<String> HANYA_ANGKA_POSITIF = str -> str.trim().isEmpty()
                || !str.chars().allMatch(Character::isDigit)
                || Integer.valueOf(str) <= 0;
        String pesanError = "Hanya menerima angka positif";

        tambahKondisiKeList(HANYA_ANGKA_POSITIF, pesanError);
        return this;
    }

    public KondisiCekInputBuilder maksimal(String in, long maksimal) {
        Predicate<String> MAKSIMAL = str -> Integer.valueOf(str) > maksimal;
        String pesanError = in + " maksimal " + maksimal;

        tambahKondisiKeList(MAKSIMAL, pesanError);
        return this;
    }

    public KondisiCekInputBuilder tidakBerulang(String in, Set<Long> data) {
        Predicate<String> ANGKA_SUDAH_ADA = str -> data.contains(Long.valueOf(str));
        String pesanError = in + " " + formatData(data) + " sudah dipilih sebelumnya";

        tambahKondisiKeList(ANGKA_SUDAH_ADA, pesanError);
        return this;
    }

    public KondisiCekInputBuilder sudahTerdaftar(String in, Set<String> data) {
        Predicate<String> NO_SUDAH_TERDAFTAR = str -> data.contains(str);
        String pesanError = in + " sudah terdaftar";

        tambahKondisiKeList(NO_SUDAH_TERDAFTAR, pesanError);
        return this;
    }

    public KondisiCekInputBuilder dan(Predicate<String> predicate, String pesanError) {
        tambahKondisiKeList(predicate, pesanError);
        return this;
    }

    public DefaultValidator ituSaja() {
        return DefaultValidator.periksaInputDenganKondisi(this);
    }

    private static String formatData(Set<Long> data) {
        StringBuilder sb = new StringBuilder();
        Object[] dataArr = data.toArray();

        int totalData = dataArr.length;
        for (int i = 0; i < totalData; i++) {
            Object num = dataArr[i];
            sb.append(num);

            if (i < (totalData - 2)) {
                sb.append(", ");
            }
            if (i == (totalData - 2)) {
                sb.append(" dan ");
            }
        }
        return sb.toString();
    }

    public void tambahKondisiKeList(Predicate<String> predicate, String pesanError) {
        listKondisi.add(Kondisi.of(predicate, pesanError));
    }

    @SuppressWarnings("unchecked")
    void proses(String input) throws Exception {
        for (Kondisi kondisi : listKondisi) {
            if (kondisi.getPredicate().test(input)) {
                throw new Exception("\n" + kondisi.getPesanError());
            }
        }
    }

    static class Kondisi {

        private final Predicate<String> predicate;
        private final String pesanError;

        private Kondisi(Predicate<String> predicate, String pesanError) {
            this.predicate = predicate;
            this.pesanError = pesanError;
        }

        public static Kondisi of(Predicate<String> predicate, String pesanError) {
            return new Kondisi(predicate, pesanError);
        }

        public Predicate getPredicate() {
            return predicate;
        }

        public String getPesanError() {
            return pesanError;
        }

    }
}
