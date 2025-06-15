package utilities;

import org.testng.annotations.DataProvider;
public class DataProviders{

@DataProvider(name= "LoginExcelData")
    public String[][] getData() throws Exception{
        String path = ".\\testData\\OpenCart_LoginData.xlsx"; // taking excel file from testData
        ExcelUtility excelUtility = new ExcelUtility(path);
        int totalRows = excelUtility.getRowCount("Sheet1");
        int totalCols = excelUtility.getCellCount("Sheet1",1);

        String loginData[][] = new String[totalRows][totalCols]; //created for two dimensional array which can store
        for(int i=1; i<=totalRows; i++){  // read the data from excel storing in two dimensional array
            for(int j=0; j<totalCols; j++){
                loginData[i-1][j] = excelUtility.getCellData("Sheet1", i,j); //i-1 as first row of sheet is for heading
            }
        }
        return loginData; //returning two dimension array

    }
}
