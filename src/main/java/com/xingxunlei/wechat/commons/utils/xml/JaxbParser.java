/**
 * JaxbParser.java
 * com.xingxunlei.wechat.commons.utils.xml
 *
 * Function： JaxbParser工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-19 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils.xml;

import java.io.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;

import java.io.InputStream;

import javax.xml.bind.Unmarshaller;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import com.sun.xml.internal.txw2.output.XmlSerializer;

import java.io.StringWriter;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import java.io.OutputStream;

import javax.xml.bind.Marshaller;

import javax.xml.bind.JAXBContext;

import org.apache.log4j.Logger;

/**
 * ClassName:JaxbParser
 * Function: JaxbParser工具类
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-19		下午4:08:54
 *
 * @see 	 
 */
@SuppressWarnings("rawtypes")
public class JaxbParser {
    private static Logger LOG = Logger.getLogger(JaxbParser.class);
    
    private Class clazz;
    private String[] cdataNode;
    
    public JaxbParser(Class clazz){
        this.clazz = clazz;
    }
    
    /**
     * toXml:Object 转为 xml 字符串
     *
     * @param  obj 待转换的对象
     * @return String  转换后的xml串
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public String toXml(Object obj) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);// 去掉报文头
            ByteArrayOutputStream  os = new ByteArrayOutputStream ();
            XMLSerializer serializer = getXMLSerializer(os);
            m.marshal(obj, serializer.asContentHandler());
            result = os.toString("UTF-8");
        } catch (Exception e) {
            LOG.error(String.format("toXml falied [%s]", e));
        }
        return result;
    }
    
    /**
     * toObj:InputStream 转为 Object
     *
     * @throws 转换异常返回 null
     * @since  CodingExample　Ver 1.1
     */
    public Object toObj(InputStream is) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller um = context.createUnmarshaller();
            Object obj = um.unmarshal(is);
            return obj;
        } catch (Exception e) {
            LOG.error(String.format("toObj falied [%s]", e));
        }
        return null;
    }
    
    /**
     * toObj:Xml String 转为 Object
     *
     * @throws 转换异常返回 null
     * @since  CodingExample　Ver 1.1
     */
    public Object toObj(String xmlStr){
        InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
        return toObj(is);
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String[] getCdataNode() {
        return cdataNode;
    }

    public void setCdataNode(String[] cdataNode) {
        this.cdataNode = cdataNode;
    }
    
    /**
     * formatCDataTag:适配cdata tag
     * @since  CodingExample　Ver 1.1
     */
    private void formatCDataTag() {
        for (int i = 0; i < cdataNode.length; i++) {
            cdataNode[i] = "^" + cdataNode[i];
        }
    }
    
    /**
     * getXMLSerializer:设置属性
     *
     * @return XMLSerializer    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    private XMLSerializer getXMLSerializer(OutputStream os){
        OutputFormat of = new OutputFormat();
        formatCDataTag();
        of.setCDataElements(cdataNode);   
        of.setPreserveSpace(true);
        of.setIndenting(true);
        of.setOmitXMLDeclaration(true);
        XMLSerializer serializer = new XMLSerializer(of);
        serializer.setOutputByteStream(os);
        return serializer;
    }

}

