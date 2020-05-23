//package pteidsample;
import pt.gov.cartaodecidadao.*;




public class CardReader {
    

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

	    PTEID_EIDCard card;
	    PTEID_ReaderContext context;
	    PTEID_ReaderSet readerSet;
	    readerSet = PTEID_ReaderSet.instance();
	    for( int i=0; i < readerSet.readerCount(); i++){
		context = readerSet.getReaderByNum(i);
		if (context.isCardPresent()){
		    card = context.getEIDCard();

		    PTEID_EId eid = card.getID();

		    
		    String nome = eid.getGivenName();
		    System.out.println(nome);

		    String nrCC = eid.getDocumentNumber();
		    System.out.println(nrCC);
		    

		    
		    

		}
	    }

	    PTEID_ReaderSet.releaseSDK();
	} catch (Exception e) {
	    e.printStackTrace();

	}
    }
    
}

