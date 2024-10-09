package ms.perpusku.cli.menu.helper;

import ms.perpusku.cli.shared.Konfirmasi;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import ms.perpusku.cli.input.TerminalInputPengembalian;
import static ms.perpusku.cli.shared.Konfirmasi.TIDAK;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Pengarang;
import ms.perpusku.service.api.BukuService;
import ms.perpusku.service.api.ServiceFactory;
import ms.perpusku.service.api.SirkulasiService;

/**
 *
 * @author MS
 */
public class InputPengembalianHelper {

    private final TerminalInputPengembalian terminalInput;
    private final BukuService bukuService;
    private final SirkulasiService sirkulasiService;

    public InputPengembalianHelper() {
        terminalInput = new TerminalInputPengembalian();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        bukuService = serviceFactory.getBukuService();
        sirkulasiService = serviceFactory.getSirkulasiService();

    }

    public List<Buku> getInputBuku() {
        var listBukuDiPinjam = getBukuDiPinjamKeyNoUrut();

        tampilkanDataBukuDiPinjam(listBukuDiPinjam);

        List<Buku> listBukuDiKembalikan = new ArrayList<>();

        Konfirmasi konfirmasi = TIDAK;
        do {
            listBukuDiKembalikan = terminalInput.getBukuDiPilih(sirkulasiService, bukuService);
            konfirmasi = terminalInput.konfirmasiBukuYangDiPilih(listBukuDiKembalikan);
        } while (konfirmasi.equals(TIDAK));

        return listBukuDiKembalikan;
    }

    private Map<Long, Buku> getBukuDiPinjamKeyNoUrut() {
        AtomicLong no = new AtomicLong(0);
        return sirkulasiService.getBukuDiPinjam()
                .map(rincianSirkulasi -> rincianSirkulasi.getBuku())
                .sorted(Comparator.comparing(Buku::getJudul))
                .collect(Collectors.toMap(b -> no.incrementAndGet(), b -> b));
    }

    private void tampilkanDataBukuDiPinjam(Map<Long, Buku> bukuDiPinjam) {
        System.out.println("");
        bukuDiPinjam.forEach(
                (noUrut, buku) -> System.out.printf("%s. %-72s oleh %s\n",
                        noUrut, buku.getJudul(), formatPengarang(buku.getPengarang())
                )
        );
        System.out.println("----------------------------------------------------------------------------");
    }

    private String formatPengarang(List<Pengarang> listPengarang) {
        return listPengarang.stream()
                .map(pengarang -> pengarang.getNama())
                .collect(Collectors.joining(", "));
    }
}
