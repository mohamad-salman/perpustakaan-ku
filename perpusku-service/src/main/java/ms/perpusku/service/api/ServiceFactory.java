package ms.perpusku.service.api;

import ms.perpusku.service.impl.BukuServiceImpl;
import ms.perpusku.service.impl.PeminjamServiceImpl;
import ms.perpusku.service.impl.SirkulasiServiceImpl;
import ms.perpusku.service.impl.PenerbitServiceImpl;
import ms.perpusku.service.impl.PengarangServiceImpl;

/**
 *
 * @author MS
 */
public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private BukuService bukuService;
    private PengarangService pengarangService;
    private PenerbitService penerbitService;
    private PeminjamService peminjamService;
    private SirkulasiService sirkulasiService;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public BukuService getBukuService() {
        if (bukuService == null) {
            bukuService = new BukuServiceImpl();
        }
        return bukuService;
    }

    public PengarangService getPengarangService() {
        if (pengarangService == null) {
            pengarangService = new PengarangServiceImpl();
        }
        return pengarangService;
    }

    public PenerbitService getPenerbitService() {
        if (penerbitService == null) {
            penerbitService = new PenerbitServiceImpl();
        }
        return penerbitService;
    }

    public PeminjamService getPeminjamService() {
        if (peminjamService == null) {
            peminjamService = new PeminjamServiceImpl();
        }
        return peminjamService;
    }

    public SirkulasiService getSirkulasiService() {
        if (sirkulasiService == null) {
            sirkulasiService = new SirkulasiServiceImpl();
        }
        return sirkulasiService;
    }

}
