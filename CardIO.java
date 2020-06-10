import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import pt.gov.cartaodecidadao.*;

public class CardIO {
    

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
	if(args.length == 0 || args.length > 3){
	    info();
	    return;
      	}
	
	try {
	    PTEID_ReaderSet.releaseSDK();
	    PTEID_ReaderSet.initSDK();

	    PTEID_ReaderContext context = PTEID_ReaderSet.instance().getReader();
	    PTEID_EIDCard card = context.getEIDCard();

	    switch(args[0]){

	    case "info":{
		//java CardIO info
		PTEID_EId eid = card.getID();
		String nrCC = eid.getDocumentNumber();
		System.out.println(nrCC);
		System.out.println(CardInfo.getCompleteName(card));
		break;
	    }

	    case "picture":{
		if(args.length < 2){
		    System.out.println("missing arguments");
		    break;
		}
		//java CardIO picture filename
		CardInfo.getPngFile(card, args[1]);
		break;
	    }

	    case "address":{
		if(args.length < 2){
		    System.out.println("missing arguments");
		    break;
		}
		//java CardIO address <Address pin>
		System.out.println(CardInfo.getAddress(card, args[1]));
		break;
	    }
	    case "write":{
		if(args.length < 2){
		    System.out.println("missing arguments");
		    break;
		}
		//java CardIO write path
		byte[] toWriteBytes = Files.readAllBytes(Paths.get(args[1]));
		String toWrite = new String(toWriteBytes, StandardCharsets.UTF_8);
		System.out.println(CardInfo.writeToCard(card, toWrite));
		break;
	    }
	    case "read":{
		if(args.length < 2){
		    System.out.println("missing arguments");
		    break;
		}
		//java CardIO read <Authentication pin>
		System.out.println(CardInfo.readFromCard(card, args[1]));
		break;
	    }
		

	    }

	    	    
	    //System.out.println(CardInfo.readFromCard(card, "2243"));
	    
	    //CardInfo.getPngFile(card, "foto.png");


	    PTEID_ReaderSet.releaseSDK();
	}
	catch (Exception e) {
	    System.out.println("Always be careful using this program, if you miss the pin more than 3 times your card will become blocked.");
	    e.printStackTrace();

	}
    }

    private static void info(){
	System.out.println("Always be careful using this program, if you miss the pin more than 3 times your card will become blocked.");
	System.out.println();
	System.out.println("\'java CardIO info\' to get basic information about card owner");
	System.out.println("\'java CardIO picture filename\' to save the picture to the given filename");
	System.out.println("\'java CardIO address <Address pin>\' to read the address information. Default: 0000");
	System.out.println("\'java CardIO write path\' to write to the card - you will need to insert the authentication pin after running twice");
	System.out.println("\'java CardIO read <Authentication pin>\' to read to the card");
	System.out.println();
	System.out.println("Common issues:");
	System.out.println("The program may crash if for some reason the drivers are not adequate. Usually running it again, turning off and on again, etc can make it work.\n The program was made to only read/write one card at a time");
    }

    
}
