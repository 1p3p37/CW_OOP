/*
package Controllers.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Stream;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;

public class ExportDataToPdf {
    public static void DoPdf() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Connect.getConnection();
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet query_set = stmt.executeQuery("SELECT *FROM product");
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:\\Users\\nejda\\Desktop\\Отчет.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(4);
            PdfPCell table_cell;

            addTableHeader(my_report_table);

            String FONT = "C:\\Users\\nejda\\IdeaProjects\\CourseWorkTry7\\src\\Controllers\\Utils\\arial.ttf";
            BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font f1=new Font(bf,11,Font.NORMAL);
            //create cell
            while (query_set.next()) {

                String idProduct = query_set.getString("idProduct");
                table_cell = new PdfPCell(new Phrase(idProduct,f1));
                my_report_table.addCell(table_cell);
                String name = query_set.getString("name");
                table_cell = new PdfPCell(new Phrase(name,f1));
                my_report_table.addCell(table_cell);
                String price = query_set.getString("price");
                table_cell = new PdfPCell(new Phrase(price,f1));
                my_report_table.addCell(table_cell);
                String number = query_set.getString("number");
                table_cell = new PdfPCell(new Phrase(number,f1));
                my_report_table.addCell(table_cell);
            }

            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            query_set.close();
            stmt.close();
            con.close();


        } catch (FileNotFoundException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LogManager.getLogger(ExportDataToPdf.class).error(e);
        }
        try
        {
//constructor of file class having file as argument
            File file = new File("C:\\Users\\nejda\\Desktop\\Отчет.pdf");
            if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not// ghjdt
            {
                System.out.println("not supported");
                LogManager.getLogger(ExportDataToPdf.class).warn("Desktop is not supported by Platform");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())         //checks file exists or not
                desktop.open(file);              //opens the specified file
        }
        catch(Exception e)
        {
            e.printStackTrace();
            LogManager.getLogger(ExportDataToPdf.class).error(e);
        }

    }
    public static void addTableHeader(PdfPTable table ) throws DocumentException, IOException {
       String FONT = "C:\\Users\\nejda\\IdeaProjects\\CourseWorkTry7\\src\\Controllers\\Utils\\arial.ttf";
       BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
       Font f1=new Font(bf,11,Font.NORMAL);
        Stream.of("Артикул", "Наименование товара", "Цена товара", "Количество")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle,f1));
                    table.addCell(header);
                });

    }
}


*/
package Controllers.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;

public class ExportDataToPdf implements Runnable {
    @Override
    public void run() {
        try {
           // Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Connect.getConnection();
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet query_set = stmt.executeQuery("SELECT *FROM product");
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:\\Users\\nejda\\Desktop\\Отчет.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(4);
            PdfPCell table_cell;

            //addTableHeader(my_report_table);
            start(my_report_table);

            String FONT = "C:\\Users\\nejda\\IdeaProjects\\CourseWorkTry7\\src\\Controllers\\Utils\\arial.ttf";
            BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font f1=new Font(bf,11,Font.NORMAL);
            //create cell
            while (query_set.next()) {

                String idProduct = query_set.getString("idProduct");
                table_cell = new PdfPCell(new Phrase(idProduct,f1));
                my_report_table.addCell(table_cell);
                String name = query_set.getString("name");
                table_cell = new PdfPCell(new Phrase(name,f1));
                my_report_table.addCell(table_cell);
                String price = query_set.getString("price");
                table_cell = new PdfPCell(new Phrase(price,f1));
                my_report_table.addCell(table_cell);
                String number = query_set.getString("number");
                table_cell = new PdfPCell(new Phrase(number,f1));
                my_report_table.addCell(table_cell);
            }

            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            query_set.close();
            stmt.close();
            con.close();


        } catch (FileNotFoundException | DocumentException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LogManager.getLogger(ExportDataToPdf.class).error(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
//constructor of file class having file as argument
            File file = new File("C:\\Users\\nejda\\Desktop\\Отчет.pdf");
            if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not// ghjdt
            {
                System.out.println("not supported");
                LogManager.getLogger(ExportDataToPdf.class).warn("Desktop is not supported by Platform");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())         //checks file exists or not
                desktop.open(file);              //opens the specified file
        }
        catch(Exception e)
        {
            e.printStackTrace();
            LogManager.getLogger(ExportDataToPdf.class).error(e);
        }

    }
    //public static void addTableHeader(PdfPTable table ) throws DocumentException, IOException {
    public void start(PdfPTable table) throws DocumentException, IOException {
        String FONT = "C:\\Users\\nejda\\IdeaProjects\\CourseWorkTry7\\src\\Controllers\\Utils\\arial.ttf";
        BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f1=new Font(bf,11,Font.NORMAL);
        Stream.of("Артикул", "Наименование товара", "Цена товара", "Количество")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle,f1));
                    table.addCell(header);
                });

    }
}



