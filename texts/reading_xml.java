import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.PrintWriter;

public class reading_xml {

	public static void main(String argv[]) {
		
		final long timeStart = System.currentTimeMillis();

		try {

			//reading the folder 
			File folder = new File("C:/Users/Kenobi/workspace/Chunker_POS_Tagger_Test/corpora");
			
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				
				if (listOfFiles[i].isFile()) {


					//reading the single xml file from the corpus		
					File fXmlFile = new File("C:/Users/Kenobi/workspace/Chunker_POS_Tagger_Test/corpora/"+listOfFiles[i].getName());
					
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					
					Document doc = dBuilder.parse(fXmlFile);

					doc.getDocumentElement().normalize();

					
					NodeList nList = doc.getElementsByTagName("t");

					
					PrintWriter writer = new PrintWriter("C:/Users/Kenobi/workspace/Chunker_POS_Tagger_Test/justTexts/"+listOfFiles[i].getName()+".txt", "UTF-8");
					
					System.out.println("Writining File # "+i);
					
					//reading the word attribute from xml file
					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							
							//writing the word into a new .txt file
							writer.println(eElement.getAttribute("word"));
//							writer.print("_");
//							writer.print(eElement.getAttribute("pos"));
//							writer.print("_");
//							writer.print(temp);
//							writer.print("_");
//							writer.println(eElement.getAttribute("lemma"));
//							writer.print("_");
//							writer.println("BN");
							
							


						}
					}
					writer.close();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		final long timeEnd = System.currentTimeMillis(); 
		final long time = (timeEnd - timeStart)/1000;
		System.out.println("Dauer des Programms: " + time + " Sek."); 
	}

}

// Alle Pfadangaben m�ssen an das eigene Verzeichnis angepasst werden.