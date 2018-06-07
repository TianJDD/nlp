package com.ultra.nlp.manage.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReadExcel {

    // 读文件
    public static void readExcel(String filename) {
        //1.首先读取excel
        //2.将excel每一行存储到一个list中，并将这些list放到一个大的list中
        //3.将每一个list中的值做拼接
        File file = new File(filename);
        try {
            List<List<String>> ans = null;
            if (file.getName().endsWith("xlsx")) {
                //处理ecxel2007
                XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
                ans = readExcel(wb, 1, 1);
            } else {
                //处理ecxel2003
                HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
                ans = readExcel(wb, 1, 1);
            }
            List<String> out = (List<String>) nginxConfig(ans);
            String FilePath = "D:\\导出.txt";
            writeFile(FilePath, out);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 整理需要的格式
     *
     * @param list
     * @return
     * @throws Exception
     */
    public static Object nginxConfig(List<List<String>> list) throws Exception {
        String location = "";
        String upstream = "";

        for (int n = 0; n < list.size(); n++) {//取出每一个list，并做字符串拼接操作
            List<String> child = list.get(n);
            String sign2 = child.get(0).substring(child.get(0).lastIndexOf("9102/") + 4, child.get(0).length());
            String sign3 = child.get(0).substring(child.get(0).lastIndexOf("9102/") + 4, child.get(0).length());
            sign3 =sign3.replace("/","_");
            location = location + "location ^~ " + sign2 + "{\n"
                    + "\t" + " proxy_pass http://dingfu_app_" + sign3 + ";\n"
                    + "}" + "\n\n";
            String server = "";
            for (String s : child) {
                server = server + "\t server " + s.substring(s.lastIndexOf("//") + 2, s.indexOf("/", 25)) + ";\n";
            }
            upstream = upstream + "upstream dingfu_app_" + sign3 + "{\n" +
                    server +
                    "}\n\n";

        }
        List<String> out = new ArrayList<>(2);
        out.add(location);
        out.add(upstream);
        return out;
    }

    /**
     * 将输出写入文件中
     *
     * @param filepath
     * @param s
     * @throws Exception
     */
    public static void writeFile(String filepath, List<String> s) throws Exception {
        File file = new File(filepath);
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            for (String st : s) {
                fos.write(st.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 读取Excel
     *生成List<String>>
     * @param wb
     * @param rowNum
     * @param cellNum
     * @return
     * @throws Exception
     */
    public static List readExcel(Workbook wb, Integer rowNum, Integer cellNum) throws Exception {
        ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            // 对于每个sheet，读取其中的每一行
            for (int rn = cellNum; rn <= sheet.getLastRowNum(); rn++) {
                Row row = sheet.getRow(rn);
                if (row == null) continue;
                ArrayList<String> curarr = new ArrayList<String>();
                for (int cn = cellNum; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn);
                    curarr.add(cell + "");
                }
                ans.add(curarr);
            }
        }
        return ans;
    }

    /**
     * 读取Excel
     *生成List<Map<String,String>>
     * @param wb
     * @param rowNum
     * @param cellNum
     * @return
     * @throws Exception
     */
    public static List readExcel2(Workbook wb, Integer rowNum, Integer cellNum) throws Exception {
        ArrayList<Map<String,String>> ans = new ArrayList<Map<String,String>>();
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            Row titleRow = sheet.getRow(0);
            // 对于每个sheet，读取其中的每一行
            for (int rn = rowNum; rn <= sheet.getLastRowNum(); rn++) {
                Row row = sheet.getRow(rn);
                if (row == null) continue;
                Map<String,String> curarr = new HashMap<>();
                for (int cn = cellNum; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn);
                    curarr.put(titleRow.getCell(cn)+"",cell+"");
                }
                ans.add(curarr);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        String filename = "D:\\导出数据.xls";
        readExcel(filename);
    }

}
