package ms.perpusku.cli.input;

import ms.perpusku.cli.shared.StringConvert;
import ms.perpusku.cli.input.validator.KondisiCekInputBuilder;
import ms.perpusku.cli.input.validator.DefaultValidator;

/**
 *
 * @author MS
 */
public class TerminalInputMenuUtama implements TerminalInput {

    public TerminalInputMenuUtama() {
    }

    public int getMenu(int totalMenu) {
        DefaultValidator validator = KondisiCekInputBuilder.create()
                .harusDiisi("Pilihan")
                .hanyaMenerimaAngkaPositif()
                .maksimal("Pilihan", totalMenu)
                .ituSaja();

        String pesanInput = "Pilih menu: ";

        return StringConvert.toInt(get(validator, pesanInput));
    }
}
