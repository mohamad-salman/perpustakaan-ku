package ms.perpusku.cli.menu.helper;

import ms.perpusku.cli.shared.Konfirmasi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ms.perpusku.cli.input.TerminalInputPeminjaman;
import static ms.perpusku.cli.shared.Konfirmasi.TIDAK;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.service.api.BukuService;
import ms.perpusku.service.api.PeminjamService;
import ms.perpusku.service.api.ServiceFactory;
import ms.perpusku.service.api.SirkulasiService;

/**
 *
 * @author MS
 */
public class InputPeminjamanHelper {

    private final TerminalInputPeminjaman terminalInput;

    private final BukuService bukuService;
    private final PeminjamService peminjamService;
    private final SirkulasiService sirkulasiService;

    public InputPeminjamanHelper() {
        terminalInput = new TerminalInputPeminjaman();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        bukuService = serviceFactory.getBukuService();
        peminjamService = serviceFactory.getPeminjamService();
        sirkulasiService = serviceFactory.getSirkulasiService();
    }

    public long getInputIdPeminjam() {
        long idPeminjam = 0;

        Konfirmasi konfirmasi = TIDAK;
        do {
            idPeminjam = terminalInput.getIdPeminjam(peminjamService);
            konfirmasi = terminalInput.konfirmasiPeminjam(idPeminjam, peminjamService);
        } while (konfirmasi.equals(TIDAK));

        return idPeminjam;
    }

    public List<Buku> getInputBuku() {
        List<Buku> list = new ArrayList<>();

        int jumlahBukuYangDiPinjam = terminalInput.getJumlahBukuYangDipinjam();

        int i = 0;
        Set<Long> idBukuSudahDiPilih = new HashSet<>();
        while (i < jumlahBukuYangDiPinjam) {
            long idBuku = 0;

            Konfirmasi konfirmasi = TIDAK;
            do {
                idBuku = terminalInput.getIdBuku(i, bukuService, idBukuSudahDiPilih, sirkulasiService);
                konfirmasi = terminalInput.konfirmasiBuku(idBuku, bukuService);
            } while (konfirmasi.equals(TIDAK));

            idBukuSudahDiPilih.add(idBuku);
            list.add(bukuService.get(idBuku).get());

            i++;
        }
        return list;
    }

}
