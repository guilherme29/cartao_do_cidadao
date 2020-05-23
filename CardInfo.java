

import pt.gov.cartaodecidadao.*;

public class CardInfo {
    

    static {
	try {
	    System.loadLibrary("pteidlibj");
	}
	catch (UnsatisfiedLinkError e) {
	    System.err.println("Native code library failed to load. \n" + e);
	    System.exit(1);
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
