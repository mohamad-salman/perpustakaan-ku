package ms.perpusku.cli.input;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ms.perpusku.cli.input.validator.DefaultValidator;
import ms.perpusku.cli.input.validator.KondisiCekInputBuilder;
import ms.perpusku.cli.shared.Konfirmasi;
import static ms.perpusku.cli.shared.Konfirmasi.TIDAK;
import static ms.perpusku.cli.shared.Konfirmasi.YA;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.service.api.BukuService;
import ms.perpusku.service.api.SirkulasiService;

/**
 *
 * @author MS
 */
public class TerminalInputPengembalian implements TerminalInput {

    public TerminalInputPengembalian() {
    }

    public List<Buku> getBukuDiPilih(SirkulasiService sirkulasiService, BukuService bukuService) {
        DefaultValidator validator = buatValidatorBukuDiPilih();

        String pesanInput = "Pilih buku yang dikembalkan (Bila lebih dari satu, pisahkan dengan koma): ";

        String in = get(validator, pesanInput);

        return Stream.of(in.split(","))
                .map(Long::valueOf)
                .filter(noUrutYangDiPilih -> noUrutYangDiPilih <= sirkulasiService.getTotalBukuDiPinjam())
                .map(noUrutYangDiPilih -> bukuService.get(noUrutYangDiPilih).get())
                .collect(Collectors.toList());
    }

    public DefaultValidator buatValidatorBukuDiPilih() {
        Predicate<String> hanyaAngkaDanKoma = str -> !str.matches("[0-9,]+");

        return KondisiCekInputBuilder.create()
                .harusDiisi("Pilihan")
                .dan(hanyaAngkaDanKoma, "Hanya menerima angka dan koma")
                .ituSaja();
    }

    public Konfirmasi konfirmasiBukuYangDiPilih(List<Buku> listBuku) {
        tampilkanBukuYangDiPilih(listBuku);

        Predicate<String> yaTidak = in -> !in.equalsIgnoreCase(YA.value()) || !in.equalsIgnoreCase(TIDAK.value());
        String pesanInput = "Apakah pilihannya sudah betul? [y/t] ";

        String in = get(yaTidak, pesanInput, "");

        return in.equals(YA.value()) ? YA : TIDAK;
    }

    private void tampilkanBukuYangDiPilih(List<Buku> listBuku) {
        System.out.println("");

        AtomicLong no = new AtomicLong(0);
        listBuku.forEach(buku -> System.out.println(no.incrementAndGet() + ". " + buku.getJudul()));
        System.out.println("----------------------------------------------------------------------------");
    }

}
