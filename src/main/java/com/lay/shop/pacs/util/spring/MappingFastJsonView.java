package com.lay.shop.pacs.util.spring;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MappingFastJsonView extends AbstractView { 
     
    /** 
     * Default content type: "application/json". 
     * Overridable through {@link #setContentType}. 
     */ 
    public static final String DEFAULT_CONTENT_TYPE = "application/json"; 

    private String encoding = "UTF-8"; 
     
    private String jsonPrefix; 

    private Boolean prettyPrint; 

    private Set<String> modelKeys; 

    private boolean extractValueFromSingleKeyModel = false; 

    private boolean disableCaching = true; 

    private boolean updateContentLength = false; 

    /** 
     * Construct a new {@code MappingFastJsonView}, setting the content type to {@code application/json}. 
     */ 
    public MappingFastJsonView() { 
        setContentType(DEFAULT_CONTENT_TYPE); 
        setExposePathVariables(false); 
    } 
     
    public String getEncoding() { 
        return encoding; 
    } 

    public void setEncoding(String encoding) { 
        this.encoding = encoding; 
    } 

    /** 
     * Specify a custom prefix to use for this view's JSON output. 
     * Default is none. 
     * @see #setPrefixJson 
     */ 
    public void setJsonPrefix(String jsonPrefix) { 
        this.jsonPrefix = jsonPrefix; 
    } 

    /** 
     * Indicates whether the JSON output by this view should be prefixed with <tt>"{} && "</tt>. 
     * Default is {@code false}. 
     * <p>Prefixing the JSON string in this manner is used to help prevent JSON Hijacking. 
     * The prefix renders the string syntactically invalid as a script so that it cannot be hijacked. 
     * This prefix does not affect the evaluation of JSON, but if JSON validation is performed 
     * on the string, the prefix would need to be ignored. 
     * @see #setJsonPrefix 
     */ 
    public void setPrefixJson(boolean prefixJson) { 
//        if (prefixJson) { 
//            if (jsonPrefix == null) { 
//                jsonPrefix = "{} && "; 
//            } 
//        } 
        this.jsonPrefix = (prefixJson ? "{} && " : null); 
    } 

    /** 
     * Whether to use the default pretty printer when writing JSON. 
     * This is a shortcut for setting up an {@code JSON} 
     * <p>The default value is {@code false}. 
     */ 
    public void setPrettyPrint(boolean prettyPrint) { 
        this.prettyPrint = prettyPrint; 
    } 

    /** 
     * Set the attribute in the model that should be rendered by this view. 
     * When set, all other model attributes will be ignored. 
     */ 
    public void setModelKey(String modelKey) { 
        this.modelKeys = Collections.singleton(modelKey); 
    } 

    /** 
     * Set the attributes in the model that should be rendered by this view. 
     * When set, all other model attributes will be ignored. 
     */ 
    public void setModelKeys(Set<String> modelKeys) { 
        this.modelKeys = modelKeys; 
    } 

    /** 
     * Return the attributes in the model that should be rendered by this view. 
     */ 
    public final Set<String> getModelKeys() { 
        return this.modelKeys; 
    } 
     
    /** 
     * Set whether to serialize models containing a single attribute as a map or whether to 
     * extract the single value from the model and serialize it directly. 
     * <p>The effect of setting this flag is similar to using {@code MappingJacksonHttpMessageConverter} 
     * with an {@code @ResponseBody} request-handling method. 
     * <p>Default is {@code false}. 
     */ 
    public void setExtractValueFromSingleKeyModel(boolean extractValueFromSingleKeyModel) { 
        this.extractValueFromSingleKeyModel = extractValueFromSingleKeyModel; 
    } 

    /** 
     * Disables caching of the generated JSON. 
     * <p>Default is {@code true}, which will prevent the client from caching the generated JSON. 
     */ 
    public void setDisableCaching(boolean disableCaching) { 
        this.disableCaching = disableCaching; 
    } 

    /** 
     * Whether to update the 'Content-Length' header of the response. When set to 
     * {@code true}, the response is buffered in order to determine the content 
     * length and set the 'Content-Length' header of the response. 
     * <p>The default setting is {@code false}. 
     */ 
    public void setUpdateContentLength(boolean updateContentLength) { 
        this.updateContentLength = updateContentLength; 
    } 
     
    @Override 
    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) { 
        setResponseContentType(request, response); 
        response.setCharacterEncoding(encoding); 
        if (this.disableCaching) { 
            response.addHeader("Pragma", "no-cache"); 
            response.addHeader("Cache-Control", "no-cache, no-store, max-age=0"); 
            response.addDateHeader("Expires", 1L); 
        } 
    } 
     
    @Override 
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) 
            throws Exception { 
        OutputStream stream = (this.updateContentLength ? createTemporaryOutputStream() : response.getOutputStream()); 
        Object value = filterModel(model); 
        writeContent(stream, value, this.jsonPrefix); 
        if (this.updateContentLength) { 
            writeToResponse(response, (ByteArrayOutputStream) stream); 
        } 
    } 

    /** 
     * Filter out undesired attributes from the given model. 
     * The return value can be either another {@link Map} or a single value object. 
     * <p>The default implementation removes {@link BindingResult} instances and entries 
     * not included in the {@link #setRenderedAttributes renderedAttributes} property. 
     * @param model the model, as passed on to {@link #renderMergedOutputModel} 
     * @return the value to be rendered 
     */ 
    protected Object filterModel(Map<String, Object> model) { 
        Map<String, Object> result = new HashMap<String, Object>(model.size()); 
        Set<String> renderedAttributes = (!CollectionUtils.isEmpty(this.modelKeys) ? this.modelKeys : model.keySet()); 
        for (Map.Entry<String, Object> entry : model.entrySet()) { 
            if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) { 
                result.put(entry.getKey(), entry.getValue()); 
            } 
        } 
        return (this.extractValueFromSingleKeyModel && result.size() == 1 ? result.values().iterator().next() : result); 
    } 

    /** 
     * Write the actual JSON content to the stream. 
     * @param stream the output stream to use 
     * @param value the value to be rendered, as returned from {@link #filterModel} 
     * @param jsonPrefix the prefix for this view's JSON output 
     * (as indicated through {@link #setJsonPrefix}/{@link #setPrefixJson}) 
     * @throws IOException if writing failed 
     */ 
    protected void writeContent(OutputStream stream, Object value, String jsonPrefix) throws IOException { 
         
        List<SerializerFeature> list = new ArrayList<SerializerFeature>(); 
        if (prettyPrint != null && prettyPrint) { 
            list.add(SerializerFeature.PrettyFormat); 
        } 
         
        SerializerFeature[] features = new SerializerFeature[list.size()]; 
        list.toArray(features); 
         
        byte[] jsonBytes = JSON.toJSONBytes(value, features); 
         
        if (jsonPrefix != null) {//此处亦可字符串相加，然后转成字节数组 
            byte[] prefixBytes = jsonPrefix.getBytes(Charset.forName("UTF-8")); 
            int prefixLength = prefixBytes.length; 
            int jsonLength = jsonBytes.length; 
            byte[] finalJsonBytes = new byte[prefixLength + jsonLength]; 
             
            System.arraycopy(prefixBytes, 0, finalJsonBytes, 0, prefixLength); 
            System.arraycopy(jsonBytes, 0, finalJsonBytes, prefixLength, jsonLength); 
            stream.write(finalJsonBytes); 
        } else { 
            stream.write(jsonBytes); 
        } 
         
        stream.flush(); 
    } 
     
}
