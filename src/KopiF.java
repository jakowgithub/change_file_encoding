import java.nio.charset.Charset;
import java.nio.file.Files;
//import java.nio.file.LinkOption;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.SortedMap;

public class KopiF {
private static int prapor=0;
private static String kodirovkaR;

public static void main(String[] args) {
if (((2==args.length)||(3==args.length)) &&
	 (null!=args[0]) && (null!=args[1])) {
    
	if (Files.exists(Paths.get(args[0].trim()))){
	 Path from = Paths.get(args[0].trim());
     Path to =   Paths.get(args[1].trim());

    if (2==args.length){
       try {Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);}
       catch (NullPointerException npe){System.out.println("NullPointerException");}
       catch (IOException ioe){System.out.println("IOException");}
}
    if (3==args.length){
	SortedMap<String, Charset> perelikKod = Charset.availableCharsets();
//perevirka vidpovidnjsti vvedenoj kodirovki dostupnoj kodirovke
	for (String dostupnaKod:perelikKod.keySet() ){
		
		if (dostupnaKod.trim().equalsIgnoreCase(args[2].trim())){
			kodirovkaR=dostupnaKod.trim();
			prapor=1;
		    break;			
}
		else prapor=0;
}
	   if (1==prapor){
		   
		   try {
		   
		   if (Files.notExists(to))Files.createFile(to);
		   else {Files.deleteIfExists(to);Files.createFile(to);}
		   
		   if ((Files.isReadable(from))&&(Files.isWritable(to))) {
            
			   List <String> ryadki= Files.readAllLines((from),Charset.forName(kodirovkaR));
			
			   for (String r2: ryadki ){
				
				   String r=r2.trim();
				   
				   if ((null!=r) && (!r.isEmpty())) {
		    	        Files.write(to, r.getBytes(), StandardOpenOption.APPEND); 
		    	        Files.write(to, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
		    		 }}}
	       else System.out.println("file is not readable or writable");

		   }catch (IOException e){ e.printStackTrace();}	
}
	   else System.out.println("This encoding is missing");   
}
}
  else System.out.println("File <from> is missing");
     }
}}
//,LinkOption.NOFOLLOW_LINKS