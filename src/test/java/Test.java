import java.io.IOException;
import java.util.ArrayList;

public class Test {

public static void main(String[] args) throws IOException {
	ExcelRead test= new ExcelRead(); 		
	ArrayList<ArrayList<String>> arr=test.xlsx_reader("F:/1.xlsx",0,1,2,3);  //����Ĳ���������Ҫ�����Щ�У�����������������
	for(int i=0;i<arr.size();i++)
	{			ArrayList<String> row=arr.get(i);
				for(int j=0;j<row.size();j++)
	{				System.out.print(row.get(j)+" ");
				}			
	
	
}
	
}
}