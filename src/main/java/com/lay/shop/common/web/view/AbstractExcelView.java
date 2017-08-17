package com.lay.shop.common.web.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.lay.shop.common.utils.PoiUtil;

public abstract class AbstractExcelView extends AbstractXlsxView{
    
    protected String path;   

    public AbstractExcelView(String path) {
        this.path = path;
    }
    
    public AbstractExcelView() {
       
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        
        Workbook workbook = null;
        try {
            workbook = PoiUtil.createWorkbook(path);
        } catch (EncryptedDocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return workbook;
    }

}