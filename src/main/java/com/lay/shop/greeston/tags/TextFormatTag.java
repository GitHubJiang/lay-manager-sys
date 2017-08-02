package com.lay.shop.greeston.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class TextFormatTag extends BaseTag {

    /** */
    private static final long serialVersionUID = -8340578302614364655L;

    private String value;
    
    private Integer maxSize = 10;

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = this.pageContext.getOut();
        String str = "";
        
        if(value.length()>maxSize){
            str = "<span title='"+value+"'>"+value.substring(0, maxSize-1)+"...</span>";
        }else{
            str = "<span>"+value+"</span>";
        }
        try {
            out.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    @Override
    public void release() {
        super.release();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
    
}
