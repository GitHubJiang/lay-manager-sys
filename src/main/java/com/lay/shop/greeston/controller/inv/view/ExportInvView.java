package com.lay.shop.greeston.controller.inv.view;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lay.shop.common.utils.PoiUtil;
import com.lay.shop.common.web.view.AbstractExcelView;
import com.lay.shop.greeston.model.inv.Inventory;

public class ExportInvView extends AbstractExcelView{
	
	public ExportInvView(String path) {
       super(path);
    }
	
	@Override
	protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
		try {
			return PoiUtil.createSXSSFWorkbook(path, 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked",})
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    List<Inventory> invList = (List<Inventory>) model.get("invList");
		Sheet sheet = workbook.getSheetAt(0);
		CellStyle cellStyle = workbook.getCellStyleAt(0);
		//居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//自动换行
		cellStyle.setWrapText(true);
        response.setContentType("application/vnd.ms-excel");
        String fileName = "库存信息.xlsx";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        for (int i = 0; i < invList.size(); i++) {
        	Row row = sheet.createRow(i+1);
        	row.createCell(0).setCellValue(invList.get(i).getBrandCode());
        	row.createCell(1).setCellValue(invList.get(i).getSkuCode());        	
        	row.createCell(2).setCellValue(invList.get(i).getQuantity());
		}
        
	}

}
