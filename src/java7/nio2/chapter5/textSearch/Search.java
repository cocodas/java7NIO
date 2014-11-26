package java7.nio2.chapter5.textSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Notes;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.record.TextHeaderAtom;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@SuppressWarnings("rawtypes")
public class Search implements FileVisitor {
	
	ArrayList<String> wordsarray = new ArrayList<>();
	ArrayList<String> documents = new ArrayList<>();
	boolean found = false;
	
	public Search(String words) {
		wordsarray.clear();
		documents.clear();
		
		StringTokenizer st = new StringTokenizer(words, ",");
		//StringTokenizer() : 나누고자 하는 문자열을 '구분자'를 기준으로 '토큰'을 만들기 위새 사용하는 클래스
		//boolean hasMoreTokens() : 반환할 트큰이 있으면 True, 없으면 False
		//String nextToken() : 다음 토큰으로 반환
		//int countTokens() : 문자열에 있는 토큰의 개수를 반환
		//String nextToken(Sting delimiters) : 다음 토큰을 반환하고 토큰 분리자를 delimiters로 설정 
		
		
		while (st.hasMoreTokens()) {
			
			wordsarray.add(st.nextToken().trim());
			//trim() : 문자열 양끝에 존재하는 공백을 제거
		}
	}
	
	void search(Path file) throws IOException{
		
		found = false;
		
		
		String name = file.getFileName().toString();
		int mid = name.lastIndexOf(".");
		String ext = name.substring(mid + 1 , name.length());
		
		//equals() VS equalsIgnoreCase() : equals()는 String 객체의 대소문자를 비교 하여 같은지 판다 equalsIgnoreCase()는 String 객체의 대소문자를 비교하지 않고 같은지 판단. 
		if (ext.equalsIgnoreCase("pdf")){
			found = searchInPDF_iText(file.toString());
			if (!found) {
				found = searchInPDF_PDFBox(file.toString());
			}			
		}
		
		if (ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx")) {
			found = searchInWord(file.toString());
		}
		
		if (ext.equalsIgnoreCase("ppt")) {
			searchInPPT(file.toString());
		}
		
		if (ext.equalsIgnoreCase("xls")) {
			searchText(file.toString());
		}
		
		if ((ext.equalsIgnoreCase("txt")) || (ext.equalsIgnoreCase("xml") || ext.equalsIgnoreCase("html")) || ext.equalsIgnoreCase("htm") || ext.equalsIgnoreCase("xhtml") || ext.equalsIgnoreCase("rtf")) {
			
			searchInText(file);
		}
		
		if (found) {
			documents.add(file.toString());
		}
	}
	
	@SuppressWarnings("finally")
	//텍스트 파일에서 검색 하기
	boolean searchInText(Path file){
		boolean flag = false;
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			String line = null;
			
			OUTERMOST:
				while ((line = reader.readLine()) != null) {
					flag = searchText(line);
					if (flag) {
						break OUTERMOST;
					}
				}
			
		} catch (IOException e) {
		} finally{
			return flag;
		}
		
	}
	
	//Excel 파일에서 검색하기
	@SuppressWarnings("finally")
	boolean searchInExcel(String file){
		Row row;
		Cell cell;
		String text;
		boolean flag = false;
		InputStream xls = null;
		
		
		try {
			xls = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(xls);
			
			int sheets = wb.getNumberOfSheets();
			
			OUTERMOST:
				for (int i = 0; i < sheets; i++) {
					HSSFSheet sheet = wb.getSheetAt(i);
					Iterator<Row> rowIterator = sheet.rowIterator();
					
					while (rowIterator.hasNext()) {
						row = rowIterator.next();
						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {
							cell = cellIterator.next();
							int type = cell.getCellType();
							if (type == HSSFCell.CELL_TYPE_STRING) {
								text = cell.getStringCellValue();
								flag = searchText(text);
								if (flag) {
									break OUTERMOST;
								}
							}
						}
					}
				}
			
		} catch (IOException e) {
		} finally{
			try {
				if (xls != null) {
					xls.close();
				}
			} catch (IOException e) {
			}
			return flag;
		}
	}
	
	
	
	//PPT 파일에서 검색하기
	@SuppressWarnings("finally")
	boolean searchInPPT(String file){
		boolean flag = false;
		InputStream fis = null;
		String text;
		
		try {
			fis = new FileInputStream(new File(file));
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSLFSlideShow show = new HSLFSlideShow(fs);
			
			SlideShow ss = new SlideShow(show);
			Slide[] slides = ss.getSlides();
			
			OUTERMOST:
				for (int i = 0; i < slides.length; i++) {
					
					TextRun[] runs = slides[i].getTextRuns();
					for (int j = 0; j < runs.length; j++) {
						TextRun run = runs[j];
						if (run.getRunType() == TextHeaderAtom.TITLE_TYPE) {
							text = run.getText();
						}else {
							text = run.getRunType() + " " + run.getText();
						}
						
						flag = searchText(text);
						
						if (flag) {
							break OUTERMOST;
						}
					}
					
					Notes notes = slides[i].getNotesSheet();
					if (notes != null) {
						runs = notes.getTextRuns();
						for (int j = 0; j < runs.length; j++) {
							text = runs[j].getText();
							flag = searchText(text);
							if (flag) {
								break OUTERMOST;
							}
						}
					}
				}
			
			
		} catch (IOException e) {
		}finally{
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
			}
			return flag;
		}
		
	}
	
	//워드 파일에서 검색하기
	@SuppressWarnings("finally")
	boolean searchInWord(String file){
		
		POIFSFileSystem fs = null;
		boolean flag = false;
		
		try {
			fs = new POIFSFileSystem(new FileInputStream(file));
			
			HWPFDocument doc = new HWPFDocument(fs);
			WordExtractor we = new WordExtractor(doc);
			String[] paragraphs = we.getParagraphText();
			
			OUTERMOST:
				for (int i = 0; i < paragraphs.length; i++) {
					
					flag = searchText(paragraphs[i]);
					if (flag) {
						break OUTERMOST;
					}			
				}
			
		} catch (Exception e) {
		}finally{
			return flag;
		}
		
	}
	
	//iText라이브러리에서  PDF파일을 검색하기
	@SuppressWarnings("finally")
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
	
	//PDBox 라이브러리에서 PDF파일을 검색 하기
	@SuppressWarnings("finally")
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
	
	//텍스트 검색하기
	private boolean searchText(String text){
		boolean flag = false;
		for (int j = 0; j < wordsarray.size(); j++) {
			if ((text.toLowerCase()).contains(wordsarray.get(j).toLowerCase())) {
				//toLowerCase() : 소문자로 변환
				//toUpperCase() : 대문자로 변환
				//boolean contains(Object o) : 매개변수로 입력되는 객체가 Collection에 있으면 true리턴, 없으면 false리턴
				
				flag = true;
				break;
			}
		}
		return flag;
	}

	
	
	
	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		//postVisitDirectory() : 디렉터리에 있는 항목을 모두 방문하거나 방문이 갑자기 중단된 후에 호출된다.
		System.out.println("Visited : " + (Path)dir);
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		//preVisitDirectory() : 디렉터리에 있는 항목을 방문하기 전에 디렉터리에 대해 호출된다
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		//visitFile() : 디렉터리에 있는 파일에 대하여 호출
		
		search((Path)file);
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		//visitFileFailed() : 파일을 어떤 이유로 접근할 수 없을때 호출
		return FileVisitResult.CONTINUE;
	}


}
