/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sd_sr
 */
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ReadExcel {
 
    @SuppressWarnings("empty-statement")
    public static void main( String [] args ) {
     Writer writer = null;
     Writer writer1 = null;
     
        try {
   int i=-1;
   int j=-1;
            InputStream fs = new BufferedInputStream(new FileInputStream("C:\\Users\\sd_sr\\OneDrive\\Documents\\projectweb\\sri.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(fs);
            XSSFSheet sheet = wb.getSheetAt(0); //sheet of excel
         
            File file = new File("C:\\Users\\sd_sr\\OneDrive\\Documents\\projectweb\\withsequence.txt"); 
            File file1 = new File("C:\\Users\\sd_sr\\OneDrive\\Documents\\projectweb\\withoutsequence.txt"); 
            writer = new BufferedWriter(new FileWriter(file));
            writer1 = new BufferedWriter(new FileWriter(file1));
            Iterator rows = sheet.rowIterator();
            while( rows.hasNext() ) {  
               // System.out.println(i);
                boolean flag=false;
                if(i==j){
                    j=i+6;
                    flag=true;
                }               
                i++;
                //XSSFCell l; 
               int l=0;
               int k = 0;
               String str;
               try{
             l = (int)wb.getSheetAt(0).getRow(j).getCell(0).getNumericCellValue();
               }catch(IllegalStateException e){
                  str=(String)wb.getSheetAt(0).getRow(j).getCell(0).getStringCellValue();
                  if(str.equals("a")||str.equals("A")){
                      l=1;
                  }
                  if(str.equals("b")||str.equals("B")){
                      l=2;
                  }
                  if(str.equals("c")||str.equals("C")){
                      l=3;
                  }
                  if(str.equals("d")||str.equals("D")){
                      l=4;
                  }
               }
               k=(j-5)+l;
                System.out.println(k+" "+i+" "+j);
                XSSFRow row = (XSSFRow) rows.next();
                System.out.println("\n");
                Iterator cells = row.cellIterator();
               
                  //writer.write("insert into Emp values(");
                while( cells.hasNext() ) {
                     
                    Cell cell = (XSSFCell) cells.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if(i==j){
                            writer.write("}"+"\r\n");
                            writer1.write("}"+"\r\n");
                            writer.write(" ");
                            writer1.write(" ");
                            System.out.println("blanck");
                            }
                            else if(k==i){
                            String s1 = " "+String.valueOf(cell.getNumericCellValue());
                                writer.write("="+s1);
                                writer1.write("="+s1);
                        }
                            else{
                             System.out.print( cell.getNumericCellValue()+"     "+cell.getColumnIndex() );
                             writer.write("~"+" "+String.valueOf(cell.getNumericCellValue()));
                             writer1.write("~"+" "+String.valueOf(cell.getNumericCellValue()));
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if(i==j){
                            writer.write("}"+"\r\n");
                            writer1.write("}"+"\r\n");
                            writer.write(" ");
                            writer1.write(" ");
                            System.out.println("blanck");
                            }
                            else if(flag){
                                String s1 = " "+cell.getStringCellValue();
                                writer1.write("::"+s1);
                                s1= s1.replace("{","\\{");
                                s1= s1.replace("::","\\::");
                                s1= s1.replace("~","\\~");
                                s1= s1.replace("=","\\=");
                                s1= s1.replace("}","\\}");
                                writer.write("::"+s1);
                            } 
                            else if(k==i){
                               String s1 = " "+cell.getStringCellValue();
                               writer1.write("="+s1);
                                s1= s1.replace("{","\\{");
                                s1= s1.replace("::","\\::");
                                s1= s1.replace("~","\\~");
                                s1= s1.replace("=","\\=");
                                s1= s1.replace("}","\\}");
                                writer.write("="+s1);
                            }
                            else{
                                String s1 = " "+cell.getStringCellValue();
                                writer1.write("~"+s1);
                                s1= s1.replace("{","\\{");
                                s1= s1.replace("::","\\::");
                                s1= s1.replace("~","\\~");
                                s1= s1.replace("=","\\=");
                                s1= s1.replace("}","\\}");
                                writer.write("~"+s1);
                            System.out.print( cell.getStringCellValue()+"\n" );
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print( cell.getBooleanCellValue()+"     " );
                            writer.write("'"+String.valueOf(cell.getBooleanCellValue()+"',"));
                            writer1.write("'"+String.valueOf(cell.getBooleanCellValue()+"',"));
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            System.out.print( "BLANK     " );
                            break; 
                        default:
                            System.out.print("Unknown cell type");
                            break;
                    }
                }
                writer.write("\r\n");
                writer1.write("\r\n");
                if(flag){writer.write("{"+"\r\n");
                writer1.write("{"+"\r\n");
                }
            }
           
             
        } catch ( IOException ex ) {
        } finally {            
            try {
            if (writer != null && writer1 != null) {
                writer.close(); 
                writer1.close();                
            }             
        } catch (IOException e) { 
        }         
        }
    }
     
}
