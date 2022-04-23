package Calendar;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.RowsExceededException;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;
import net.sf.json.JSONObject;


class CalendarTest {

    @org.junit.jupiter.api.Test

    void nextDay() {
        File file = new File(".../main/resources/CalendarTest.xls");
        try {
            // 创建输入流，读取Excel
            //InputStream is = new FileInputStream(file);
            //FileOutputStream out=new FileOutputStream(file);
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(file);
            WritableWorkbook book = Workbook.createWorkbook(file, wb);
            // 每个页签创建一个Sheet对象
            Sheet sheet = wb.getSheet(0);
            WritableSheet wsheet = book.getSheet(0);
            int right_num=0;
            int error_num=0;
            JSONObject returnedjson =new JSONObject();
            List<returnformat> jsonObjectList=new ArrayList<>();
            // sheet.getRows()返回该页的总行数
            for (int i = 1; i < sheet.getRows(); i++) {
                int y=Integer.valueOf(sheet.getCell(1,i).getContents());
                int m=Integer.valueOf(sheet.getCell(2,i).getContents());
                int d=Integer.valueOf(sheet.getCell(3,i).getContents());
                String result=new Calendar(y,m,d).nextDay();
                Label label = new Label(5, i, result);
                wsheet.addCell(label);
                System.out.println(sheet.getCell(4,i).getContents());
                System.out.println(result);
                WritableCellFormat format = new WritableCellFormat();
                format.setBackground(Colour.RED); //背景色

                if(result.equals(String.valueOf(sheet.getCell(4,i).getContents())))
                {
                    Label label1 = new Label(6, i, String.valueOf(result.equals(String.valueOf(sheet.getCell(4,i).getContents()))));
                    wsheet.addCell(label1);
                    right_num++;

                }
                else
                {
                    Label label1 = new Label(6, i, String.valueOf(result.equals(String.valueOf(sheet.getCell(4,i).getContents()))),format);
                    wsheet.addCell(label1);
                    error_num++;
                    returnformat r=new returnformat(Integer.toString(y)+"/"+Integer.toString(m)+"/"+Integer.toString(d),String.valueOf(sheet.getCell(4,i).getContents()),result);
                    jsonObjectList.add(r);
                }
            }
            System.out.println(right_num);
            System.out.println(error_num);
            returnedjson.put("right_num",right_num);
            returnedjson.put("error_num",error_num);
            returnedjson.put("error_info",jsonObjectList);
            System.out.println(returnedjson);
            book.write();
            book.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}