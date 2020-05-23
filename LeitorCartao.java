//package pteidsample;
import pt.gov.cartaodecidadao.*;




public class LeitorCartao {
    

    static {
	try {
	    System.loadLibrary("pteidlibj");
	} catch (UnsatisfiedLinkError e) {
	    System.err.println("Native code library failed to load. \n" + e);
	    System.exit(1);
	}
    }


    
    public static void main(String[] args) {
	try {
	    PTEID_ReaderSet.initSDK();

	    PTEID_ReaderSet readerSet = PTEID_ReaderSet.instance();

	    PTEID_ReaderContext context = readerSet.getReader();
	    
	    PTEID_EIDCard card = context.getEIDCard();

	    PTEID_EId eid = card.getID();

	    String nome = eid.getGivenName();

	    String nrCC = eid.getDocumentNumber();

	    System.out.println(nome);
	    System.out.println(nrCC);

	    
	    //System.out.println("hello world");
	    

	    

	    PTEID_ReaderSet.releaseSDK();
	} catch (Exception e) {
	    e.printStackTrace();

	}
    }
}

