
import pt.gov.cartaodecidadao.*;

public class CardReader {
    

    static {
	try {
	    System.loadLibrary("pteidlibj");
	}
	catch (UnsatisfiedLinkError e) {
	    System.err.println("Native code library failed to load. \n" + e);
	    System.exit(1);
	}
    }
    
    public static void main(String[] args) {
	try {
	    PTEID_ReaderSet.releaseSDK();
	    PTEID_ReaderSet.initSDK();

	    PTEID_ReaderContext context = PTEID_ReaderSet.instance().getReader();
	    PTEID_EIDCard card = context.getEIDCard();
	    PTEID_EId eid = card.getID();

	    //System.out.println(CardInfo.getCompleteName(card));
	    String nrCC = eid.getDocumentNumber();
	    System.out.println(nrCC);
	    //System.out.println(CardInfo.getAddress(card, "0000"));
	    
	    String helloworld = "helloworld";
	    
	    //System.out.println(CardInfo.writeToCard(card, "2243", helloworld));
	    System.out.println(CardInfo.readFromCard(card, "2243"));
	    
	    //CardInfo.getPngFile(card, "foto.png");
	    PTEID_ReaderSet.releaseSDK();
	}
	catch (Exception e) {
	    e.printStackTrace();

	}
    }



    
}
