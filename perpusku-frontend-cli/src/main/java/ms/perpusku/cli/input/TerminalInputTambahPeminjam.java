package ms.perpusku.cli.input;

import ms.perpusku.cli.input.validator.KondisiCekInputBuilder;
import ms.perpusku.cli.input.validator.DefaultValidator;

/**
 *
 * @author MS
 */
public class TerminalInputTambahPeminjam implements TerminalInput {

    public TerminalInputTambahPeminjam() {
    }

    public String getNama() {
        DefaultValidator validator = KondisiCekInputBuilder.create()
                .harusDiisi("Nama")
                .hanyaMenerimaHuruf()
                .ituSaja();

        String pesanInput = "Masukkan nama: ";

        return get(validator, pesanInput);
    }

    public String getTelp() {
        DefaultValidator validator = KondisiCekInputBuilder.create()
                .hanyaMenerimaAngkaNolKeAtas()
                .ituSaja();

        String pesanInput = "Masukkan no telp: ";

        return get(validator, pesanInput);
    }
}
