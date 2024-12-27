package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExternalSource extends JavaHelper{

	public File MyFile;
	public FileInputStream inputStream;
	public FileOutputStream outputStream;
	public String Convert;
	public Workbook wb;
	public Sheet sh;
	
	public void OnlyRead()throws Exception
	{
		MyFile = new File(System.getProperty("user.dir") + env.getProperty("ExternalFile"));
		logger.info("Your External File Path is(For Read):- "+ MyFile);
		inputStream = new FileInputStream(MyFile);
		logger.info("Inputstream is done");
		wb = new XSSFWorkbook(inputStream);
		logger.info("Workbook is fetched");
		sh = wb.getSheet("Sheet1");
		logger.info("Sheet getting is done");
		int RowCount = sh.getLastRowNum()-sh.getFirstRowNum();
		logger.info("Total RowCount in Sheet is:- "+ RowCount);
		for (int i=0;i<= RowCount;i++)
		{
			Row row = sh.getRow(i);
			  for (int j = 0; j < row.getLastCellNum(); j++) 
			  {
		            System.out.print(row.getCell(j).getStringCellValue()+"|| ");
		        }
		        System.out.println();
		}
		Cell YourSearchValue = sh.getRow(1).getCell(0);
		Convert = YourSearchValue.getStringCellValue();
		logger.info("Fetch value from Extrenal source is:- "+ Convert);
		}
	
	public void OnlyWrite() throws Exception
	{
		MyFile = new File(System.getProperty("user.dir") + env.getProperty("ExternalFile"));
		logger.info("Your External File Path is(For Write):- "+ MyFile);
		inputStream = new FileInputStream(MyFile);
		logger.info("Inputstream is done for Write");
		wb = new XSSFWorkbook(inputStream);
		logger.info("Inputstream is done for Write");
		sh = wb.getSheet("Sheet1");
		logger.info("Sheet getting is done for Write");
		Cell WriteCell = sh.getRow(1).createCell(1);
		logger.info("New Cell creation is done");
		WriteCell.setCellValue("");
		WriteCell.setCellValue("PASS");
		logger.info("Value set is done");
		outputStream = new FileOutputStream(MyFile);
		wb.write(outputStream);
		logger.info("Outputstream is done for Write");
		outputStream.close();
		logger.info("File is closed");
	}
}