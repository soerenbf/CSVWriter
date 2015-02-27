import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
	
	private String fileName;
	private String columns[];
	private FileWriter writer;
	
	private boolean fileCreated = false;
	
	public CSVWriter (String fileName) {
		this(fileName, null);
	}
	
	public CSVWriter (String fileName, String columns[]) {
		this.fileName = fileName;
		this.columns = columns;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public String[] getColumns() {
		return this.columns;
	}
	
	public void writeToFile(String values[]) throws IOException {
		if (!fileCreated) {
			generateFile(this.fileName);
		}
		
		//First check if the amount of values given matches the amount of columns. If no columns are given just write whatever is sent.
		if (values.length == this.columns.length || this.columns == null) {
			this.writeRow(values);
		}
	}
	
	public void closeWriter() throws IOException {
		this.writer.flush();
		this.writer.close();
	}
	
	private void generateFile(String fileName) throws IOException {
		this.writer = new FileWriter(fileName);
		
		if (columns != null) {
			this.writeRow(this.columns);
		}
		
		fileCreated = true;
	}
	
	private void writeRow(String values[]) throws IOException {
		for (int i = 0; i < values.length; i++) {
			writer.append(values[i].toString());
			
			if (i < values.length - 1) {
				writer.append(",");
			}
		}
		
		writer.append("\n");
	}

}
