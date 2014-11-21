package java7.nio2.chapter5.PDFFileSearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.management.loading.PrivateClassLoader;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Search implements FileVisitor {
	
	ArrayList<String> wordsarray = new ArrayList<>();
	ArrayList<String> documents = new ArrayList<>();
	boolean found = false;
	
	public Search(String words) {
		wordsarray.clear();
		documents.clear();
		
		StringTokenizer st = new StringTokenizer(words, ",");
		while (st.hasMoreTokens()) {
			
			wordsarray.add(st.nextToken().trim());			
		}
	}
	
	void Search(Path file) throws IOException{
		
		found = false;
		
		
		String name = file.getFileName().toString();
		int mid = name.lastIndexOf(".");
		String ext = name.substring(mid + 1 , name.length());
		
		if (ext.equalsIgnoreCase("pdf")){
			found = searchInPDF_iText(file.toString());
			if (!found) {
				found = searchInPDF_PDFBox(file.toString());
			}			
		}
		if (found) {
			documents.add(file.toString());
		}
	}
	
	boolean searchInPDF_iText(String file){
		
		PdfReader reader = null;
		boolean flag = false;
		
		try {
			reader = new PdfReader(file);
			int n = reader.getNumberOfPages();
			
			OUTERMOST:
				for (int i = 0; i <= n; i++) {
					String str = PdfTextExtractor.getTextFromPage(reader, i);
					
					flag = searchText(str);
					if (flag) {
						break OUTERMOST;
					}
				}	
		} catch (Exception e) {
		}finally{
			if (reader != null) {
				reader.close();
			}
			
			return flag;
		}	
	}
	
	boolean searchInPDF_PDFBox(String file){
		
		PDFParser parser = null;
		String parsedText = null;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		boolean flag = false;
		int page = 0;
		
		File pdf = new File(file);
		
		try {
			parser = new PDFParser(new FileInputStream(pdf));
			parser.parse();
			
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			
			OUTERMOST:
				while (page < pdDoc.getNumberOfPages()) {
					page++;
					pdfStripper.setStartPage(page);
					pdfStripper.setEndPage(page + 1);
					parsedText = pdfStripper.getText(pdDoc);
					
					flag = searchText(parsedText);
					if (flag) {
						break OUTERMOST;
					}
				}
			
			
		} catch (Exception e) {
		} finally{
			try {
				if (cosDoc != null) {
					cosDoc.close();
				}
				if (pdDoc != null) {
					pdDoc.close();
				}
			} catch (Exception e) {
			}
			
			return flag;
		}
	}
	
	private boolean searchText(String text){
		boolean flag = false;
		for (int i = 0; i < wordsarray.size(); i++) {
			if ((text.toLowerCase()).contains(wordsarray.get(i).toLowerCase())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	
	
	
	
	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		
		Search((Path)file);
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		
		System.out.println("Visited : " + (Path)dir);
		return FileVisitResult.CONTINUE;
	}

}
