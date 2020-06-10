

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

    static String getAddress(PTEID_EIDCard card, String pinCode){
	try {
	    PTEID_EId eid = card.getID();
	    String address = "";
	    
	    PTEID_ulwrapper triesLeft = new PTEID_ulwrapper(-1);
	    PTEID_Pins pins = card.getPins();
	    PTEID_Pin pin = pins.getPinByPinRef(PTEID_Pin.ADDR_PIN);

	    if (pin.verifyPin(pinCode, triesLeft, true)){
       		PTEID_Address addr =  card.getAddr();

		String country = addr.getCountryCode(); 
		String district = addr.getDistrict();
		String municipality  =  addr.getMunicipality();
		String civilParish = addr.getCivilParish();
		String street = addr.getStreetName();
		String doorNo = addr.getDoorNo();
		String floor = addr.getFloor();
		String side = addr.getSide();
		String locality = addr.getLocality();
		String zip4 = addr.getZip4();
		String zip3 = addr.getZip3();
		String postalLocality = addr.getPostalLocality();

		address = country + " " +
		    district + " " +
		    municipality + " " +
		    civilParish + " " +
		    street + " " +
		    doorNo + " " +
		    floor + " " +
		    side + " " +
		    locality + " " +
		    zip4 + "-" + zip3 + " " +
		    postalLocality;
	      		
	    }
	    
	    
	    return address;
	}
	catch(PTEID_Exception e) {
	    e.printStackTrace();
	    return "";
	}



    }

    static boolean writeToCard(PTEID_EIDCard card, String pinCode, String toWrite){
	try {

	    PTEID_ulwrapper triesLeft = new PTEID_ulwrapper(-1);
	    	    	    
	    // Writing
	    PTEID_ByteArray pb = new PTEID_ByteArray(toWrite.getBytes(), toWrite.getBytes().length);
	    boolean bOk = card.writePersonalNotes(pb, card.getPins().getPinByPinRef(PTEID_Pin.AUTH_PIN));
	    return bOk;

		//}
	}
	catch(PTEID_Exception e){
	    e.printStackTrace();
	    
	}
	return false;
	
    }

    static String readFromCard(PTEID_EIDCard card, String pinCode){
	try {
	    return card.readPersonalNotes();
	    	    
	}
	catch(PTEID_Exception e){
	    e.printStackTrace();
	    
	}
	return null;
    }


}
