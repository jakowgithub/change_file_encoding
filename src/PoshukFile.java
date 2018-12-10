import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PoshukFile {

	public static void main(String[] args) {

	if ((2==args.length) &&
	    (null!=args[0])  &&
	    (null!=args[1])){
	
		Path startDir = Paths.get(args[0].trim());
	
	String fileName=args[1].trim();
	
    if (fileName.contains("*"))	fileName = fileName.substring(0, fileName.indexOf('*'));
	
    final String fs=fileName;
    
    try (Stream <Path> shlyax = 
    		Files.find(	startDir, Integer.MAX_VALUE,
                      (p, a) -> (a.isRegularFile()) && 
                                (p.toString()
    		                      .substring(p.toString().lastIndexOf(System.getProperty("file.separator"))+1, p.toString().length())
    		                      .startsWith(fs)))) {
	//p-path, a-atribut.
   // print first 100 :
	shlyax.limit(100).forEach(System.out::println);

    }catch (IOException ioe){System.out.println("IOException"); ioe.printStackTrace();}
}
}}
