
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
	    PTEID_ReaderSet.initSDK();

	    PTEID_ReaderContext context = PTEID_ReaderSet.instance().getReader();
	    PTEID_EIDCard card = context.getEIDCard();
	    PTEID_EId eid = card.getID();
	    
	    System.out.println(getCompleteName(card));
	    
	    String nrCC = eid.getDocumentNumber();
	    System.out.println(nrCC);
	    
	    getPngFile(card, "foto.png");
		
	    PTEID_ReaderSet.releaseSDK();
	}
	catch (Exception e) {
	    e.printStackTrace();

	}
    }


    static void getPngFile(PTEID_EIDCard card, String filename){
	try {
	    PTEID_EId eid = card.getID();
	    PTEID_Photo photoObj = eid.getPhotoObj();
	    PTEID_ByteArray ppng = photoObj.getphoto();	// formato PNG
	    ppng.writeToFile(filename);
	    return;
	}
	catch (PTEID_Exception e){
	    e.printStackTrace();
	    return;
	}
		   
    }
    
    static String getCompleteName(PTEID_EIDCard card){
	try {
	    PTEID_EId eid = card.getID();
	    return eid.getGivenName() + " " + eid.getSurname();	
	}
	catch(PTEID_Exception e) {
	    e.printStackTrace();
	    return "";
	}
    }

    
}
