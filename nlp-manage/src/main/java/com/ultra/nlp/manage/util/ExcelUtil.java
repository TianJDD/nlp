package com.ultra.nlp.manage.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @Auther: guyuefei
 * @Date: 2018/5/10 12:55
 * @Description:
 * @Usefor:
 * @param:
 * @Response:
 */
public class ExcelUtil {

    public static Object ExcelTemplet (List<String> cells){
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("接入服务信息录入");
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        //style.setFillPattern(CellStyle.SOLID_FOREGROUND);//填充单元格
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        Row row = sheet.createRow(0);
        Cell cell ;
        for(int n = 0; n < cells.size() ; n ++){
            cell = row.createCell(n);
            cell.setCellValue(cells.get(n));
            cell.setCellStyle(style);
        }
        return workbook;
    }
    /**
     * 依据windows或是linux环境，截取出物理路径。
     * @Auther: zy
     * @return
     */
    public static String subFilePath(String path) {
        // windows路径返回 ：file:/E:/workSpace3.6/SpringTM/WebRoot/WEB-INF/classes/
        // Linux路径返回 : file:/workSpace3.6/SpringTM/WebRoot/WEB-INF/classes/
        String[] paths = path.split(":");
        if (paths.length == 3) {// 如果存在两个":"字符，说明为windows环境，需要去掉"file:/"
            path = path.substring(path.indexOf(":") + 2);
        } else {// 否则就为Linux环境，需要去掉"file:"
            path = path.substring(path.indexOf(":") + 1);
        }
        return path;
    }

    /**
     * 当前java类，所在的物理路径
     * @Auther: zy
     * @return
     */
    public String currentPhysicalPath() {
        return subFilePath(this.getClass().getResource("").toString());
    }

}
