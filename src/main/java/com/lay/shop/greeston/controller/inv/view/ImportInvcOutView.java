package com.lay.shop.greeston.controller.inv.view;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.lay.shop.common.utils.PoiUtil;
import com.lay.shop.common.web.view.AbstractExcelView;

public class ImportInvcOutView extends AbstractExcelView{
	
	public ImportInvcOutView(String path) {
       super(path);
    }
	
	@Override
	protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
		try {
			return PoiUtil.createXSSFWorkbook(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    String fileName = "销售出库导入模板.xlsx";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);     
	}

}
