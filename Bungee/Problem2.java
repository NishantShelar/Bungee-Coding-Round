import java.io.FileWriter;
import java.io.Reader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import com.opencsv.CSVWriter;

class Problem2
 {

	private static final String csvFile = "filteredCountry.csv";
	private static final String newCSV = "lowPrice.csv";
	private static float FIRST_low = 0;
	private static float SECOND_low = 0;
	private static String Temp_SKU = "";
	private static int flag = 0;
	

	public static void main(String args[]) throws IOException
	 {

		FileWriter writer = null;
		CSVParser csvParser = null;
		Reader reader = null;

		try
		 {
			reader = Files.newBufferedReader(Paths.get(csvFile));
			csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
			writer = new FileWriter(newCSV);
			final CSVWriter csvWriter = new CSVWriter(writer);
			FIRST_low = SECOND_low = Float.MAX_VALUE;

			if (flag == 0) 
			{
			flag = 1;
			csvWriter.writeNext(new String[] { "SKU", "FIRST_MINIMUM_PRICE", "SECOND_MINIMUM_PRICE" });
			}
			
			csvParser.forEach(C -> 
			{
				Float PRICE = Float.parseFloat(C.get(5));

				if (SECOND_low != Float.MAX_VALUE && !(C.get(0).equals(Temp_SKU))) 
				{
					
					csvWriter.writeNext(
							new String[] { Temp_SKU, String.valueOf(FIRST_low), String.valueOf(SECOND_low) });
				}

				
				if (C.get(0).equals(Temp_SKU)) 
				{
					if (FIRST_low > PRICE)
					 {
						SECOND_low = FIRST_low;
						FIRST_low = PRICE;
					}
					 else if (SECOND_low > PRICE && (SECOND_low> FIRST_low))
					 {
						SECOND_low = PRICE;
					}
				}
				 else
				 {
					Temp_SKU = C.get(0);
					FIRST_low = PRICE;
					SECOND_low = Float.MAX_VALUE;
				}
			});
			csvWriter.close();
		}
		 catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			writer.close();
			csvParser.close();
		}
	}
}