import java.util.*;
import java.io.*;

class NucleicAcid
{
	public static void main(String[] args) throws IOException {

		saveFile(encode(getData()));
//		saveFile(translate(getData())); //test
	}

//======================================================================

	public static String getData() throws IOException {
		try{
			print("Select DNA raw data file: ");
			Scanner var = new Scanner(System.in); //select raw input file
			String filename = var.next();
			File rawDNA = new File(filename);

			Scanner scanDNA = new Scanner(rawDNA);
			String rawDNAsequence = "";

			while(scanDNA.hasNext()) {
				rawDNAsequence += scanDNA.next();
			}

			return rawDNAsequence;
		}
		catch (FileNotFoundException e) {
			print("File not found!"); System.exit(0);
		}
		return null;
	}

//======================================================================

	public static String encode(String rawDNAsequence) {
		char c;

		int A = 0,
			C = 0,
			G = 0,
			T = 0,
			U = 0,
			N = 0;

		//construct html document
		String	colour,
				rDNA = rawDNAsequence,
				eDNA = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'"
					 + "'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n"
					 + "<style TYPE='text/css'>\n<!--\n"
					 + "font {\nfont-family: 'Courier New', Courier, monospace;\n"
					 + "font-size: 14px;\nfont-weight:bold;}\n"
					 + "p {\nfont-size: 12px;\nfont-weight:normal;}\n"
					 + "bp {\nmargin-left: 15px;}\n-->\n</style>\n";

		for(int i=0;i<rDNA.length();i++) {
			//block counter
			if(i%10 == 0) eDNA += "<bp />";
			if(i==0) eDNA += "<bp />5' Base Sequence<br /><font>&nbsp&nbsp" + i + "</font><bp />";
			else if(i==50) eDNA += "<br /><font>&nbsp" + i + "</font><bp />";
			else if(i%50 == 0) eDNA += "<br /><font>" + i + "</font><bp />";

			c = rDNA.charAt(i);
			c = Character.toUpperCase(c);

			//colour code
/*				 if(c == 'A') { colour = "#CC0000"; A++; }
			else if(c == 'C') { colour = "#028A17"; C++; }
			else if(c == 'G') { colour = "#FF9900"; G++; }
			else if(c == 'T') { colour = "#281FD7"; T++; }
			else if(c == 'U') { colour = "#CC0099"; U++; }
			else { colour = "#000000"; N++; }
*/
			switch(c) { //switch implementation
				case 'A':
					colour = "#CC0000";
					A++;
					break;
				case 'C':
					colour = "#028A17";
					C++;
					break;
				case 'G':
					colour = "#FF9900";
					G++;
					break;
				case 'T':
					colour = "#281FD7";
					T++;
					break;
				case 'U':
					colour = "#CC0099";
					U++;
					break;
				default:
					colour = "#000000";
					N++;
			}
			eDNA += "<font color ='" + colour + "'>" + c + "</font>";
		}

		eDNA += "<p>"
		+ "A - adenine " + A
		+ "<bp />C - cytosine " + C
		+ "<bp />G - guanine " + G
		+ "<bp />T - thymine "+ T
        + "<bp />U = uracil "+ U
        + "<bp />N = other "+ N
        + "<bp />Total BP " + (A+C+G+T+N)
        + "</p>\n";

		//do reverse transcription
		eDNA += translate(rDNA);

		//TODO
		//check repeating units ??
		//show amino acids by 3 letter code

//		print(eDNA); //test
		return eDNA;
	}

//======================================================================

	public static String translate(String rawDNAsequence) {
		String 	colour,
				rDNA = rawDNAsequence,
				tDNA = "",
				eDNA = "";
		char c;

		//translate DNA
		for(int i=rDNA.length()-1;i+1>0;i--) {
			c = rDNA.charAt(i);
			c = Character.toUpperCase(c);

			//make IUPAC consistant
/*				 if(c == 'A') tDNA += 'T';
			else if(c == 'C') tDNA += 'G';
			else if(c == 'G') tDNA += 'C';
			else if(c == 'T') tDNA += 'A';
			else if(c == 'U') tDNA += 'A';
			else tDNA += 'N';
*/
			switch(c) { //switch implementation
				case 'A':
					tDNA += 'T';
					break;
				case 'C':
					tDNA += 'G';
					break;
				case 'G':
					tDNA += 'C';
					break;
				case 'T':
					tDNA += 'A';
					break;
				case 'U':
					tDNA += 'A';
					break;
				default:
					tDNA += 'N';
			}
		}

		for(int i=0;i<tDNA.length();i++) {
			//block counter
			if(i%10 == 0) eDNA += "<bp />";
			if(i==0) eDNA += "<bp />5' DNA Translation<br /><font>&nbsp&nbsp" + i + "</font><bp />";
			else if(i==50) eDNA += "<br /><font>&nbsp" + i + "</font><bp />";
			else if(i%50 == 0) eDNA += "<br /><font>" + i + "</font><bp />";

			c = tDNA.charAt(i);
			c = Character.toUpperCase(c);

			//colour code
/*				 if(c == 'A') { colour = "#CC0000"; }
			else if(c == 'C') { colour = "#028A17"; }
			else if(c == 'G') { colour = "#FF9900"; }
			else if(c == 'T') { colour = "#281FD7"; }
			else { colour = "#000000"; }
*/
			switch(c) { //switch implementation
				case 'A':
					colour = "#CC0000";
					break;
				case 'C':
					colour = "#028A17";
					break;
				case 'G':
					colour = "#FF9900";
					break;
				case 'T':
					colour = "#281FD7";
					break;
				default:
					colour = "#000000";
			}

			eDNA += "<font color ='" + colour + "'>" + c + "</font>";
		}
//		print(eDNA); //test
		return eDNA;
	}

//======================================================================

	public static void saveFile(String eDNA) throws IOException {
		try {
			String encodedDNAsequence = eDNA;

			print("Save encoded DNA as: ");
			Scanner var = new Scanner(System.in);
			String filename = var.next().trim();

			File encodedDNA = new File(filename + ".html"); //PATH = *.html
			FileWriter fstream = new FileWriter(encodedDNA);
 			BufferedWriter out = new BufferedWriter(fstream);
  			out.write(encodedDNAsequence);
  			out.close();

			print("Encoded DNA saved as: " + encodedDNA.getPath());
		}
		catch (IOException e) {
			print("error");
		}
	}

//======================================================================

	public static void print(String x){System.out.print(x);}
	public static void print(int x){System.out.print(x);}
}