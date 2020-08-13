package com.yiyuankafei.wx.athena.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.yiyuankafei.wx.athena.entity.wx.Message;

public class MessageUtil {

	/**
	 *  
	 *  将xml转化为map 
	 **/
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		InputStream inputStream = request.getInputStream();
		Document doc = reader.read(inputStream);
		Element root = doc.getRootElement();// 得到根节点
		List<Element> list = root.elements();// 根节点下的所有的节点
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		inputStream.close();
		return map;
	}

	/**
	 * 
	 *  将消息内容转变为xml 
	 **/
	public static String objectToXml(Message message) {
		XStream xStream = new XStream();
		xStream.alias("xml", message.getClass());
		return xStream.toXML(message);
	}

}
