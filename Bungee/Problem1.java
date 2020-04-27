import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import com.opencsv.CSVWriter;


class Problem1
 {

	private static final String csvFile = "C:\Users\nisha\Desktop\Bungee\main.csv";
	private static final String newCSV = "C:\Users\nisha\Desktop\Bungee\filteredCountry.csv";

	public static void main(String args[]) throws IOException {
		int flag = 0;

		FileWriter writer = null;
		CSVWriter csvWriter = null;
		
		CSVParser csvParser = null;
		Reader reader = null;

		try 
		{
			reader = Files.newBufferedReader(Paths.get(csvFile));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			writer = new FileWriter(newCSV);
			csvWriter = new CSVWriter(writer);
			
			
			for (CSVRecord csvRecord : csvParser)
			 {
				if (csvRecord.get(8).contains("USA") || flag == 0) 
				{
					flag = 1;
					csvWriter.writeNext(new String[] { csvRecord.get(0), csvRecord.get(1), csvRecord.get(2),
							csvRecord.get(3), csvRecord.get(4), csvRecord.get(5).replaceAll("[^a-zA-Z0-9.]", "") , csvRecord.get(6), csvRecord.get(7),
							csvRecord.get(8) });
				}
			}
		}
		 catch (Exception e)
		 {
			
			e.printStackTrace();
		} 
		finally
		 {
			csvWriter.close();
			writer.close();
			csvParser.close();
		}
	}
}