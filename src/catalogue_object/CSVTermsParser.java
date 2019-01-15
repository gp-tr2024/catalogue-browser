package catalogue_object;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import catalogue.Catalogue;
import term_code_generator.CodeGenerator;
import term_code_generator.TermCodeException;

/**
 * JUST FOR CAT. MANAGERS
 * 
 * Parse a csv file in order to extract the terms to be added The terms csv file
 * is composed by 4 columns: 
 * parentCode, termExtededName, scopeNotes, scientificName
 * 
 * @author shahaal
 *
 */
public class CSVTermsParser {

	private static final Logger LOGGER = LogManager.getLogger(CSVTermsParser.class);

	private Catalogue catalogue;
	private String delim;
	private String currentLine;
	private BufferedReader reader;

	public CSVTermsParser(Catalogue catalogue, String filename) {

		this.catalogue = catalogue;

		File file = new File(filename);

		if (!file.exists()) {
			LOGGER.error("The file " + filename + " does not exist");
			return;
		}

		try {

			// prepare the reader
			reader = new BufferedReader(new FileReader(filename));

			// skip headers
			reader.readLine();

		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("Cannot open file=" + filename, e);
		}

		/*
		 * STANDARDIZED CHARACTERS SEPARATOR the csvSplitBy String is used to smartly
		 * separate the data inside a csv file by columns. Nb the csv file has not
		 * columns separator but instead use specific characters to denote them.
		 */
		this.delim = "\t";
	}

	public boolean hasNext() throws IOException {

		currentLine = reader.readLine();

		return currentLine != null;
	}

	/**
	 * Parse a line and get the current term
	 * 
	 * @param inputFilename
	 * @param delim
	 * @throws TermCodeException 
	 * @throws IOException
	 */
	public int startToImportTerms() throws TermCodeException {

		int termsAdded=1;
		try {
			
			while(hasNext()) {

				// parse the current line
				StringTokenizer st = new StringTokenizer(currentLine, delim);

				// if wrong number of tokens return
				if (st.countTokens() < 4) {
					LOGGER.error("Wrong number of columns, expected 4, found : " + st.countTokens());
					return termsAdded;
				}

				// get the a new code for the term using the catalogue term code mask
				String code = CodeGenerator.getTermCode( catalogue.getTermCodeMask() );
				
				CatalogueObject parent = (CatalogueObject) catalogue.getTermByCode(st.nextToken());
				
				String termExtendedName = st.nextToken();
				String scopeNotes = st.nextToken();
				String scientificName = st.nextToken();
				
				// insert the term in the catalogue
				catalogue.addNewTerm(code, parent, catalogue.getMasterHierarchy(), termExtendedName, scopeNotes, scientificName);
				
				termsAdded+=1;
				
			}

			reader.close();
			catalogue.refresh();
			
		} catch (IOException e) {
			LOGGER.error("The file has not terms inside! ");
			e.printStackTrace();
		}
		
		return termsAdded;
	}
}
