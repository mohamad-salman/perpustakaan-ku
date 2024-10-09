package ms.perpusku.cli.menu.helper;

import ms.perpusku.cli.input.TerminalInputTambahPeminjam;

/**
 *
 * @author MS
 */
public class InputTambahPeminjamHelper {

    private final TerminalInputTambahPeminjam terminalInput;

    public InputTambahPeminjamHelper() {
        terminalInput = new TerminalInputTambahPeminjam();
    }

    public String getInputNama() {
        return terminalInput.getNama();
    }

    public String getInputTelp() {
        return terminalInput.getTelp();
    }

}
