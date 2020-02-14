package Tools.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 把对象转成json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStr(Object obj) {
		String json_str = "";
		try {
			json_str = objectMapper.writer().writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json_str;
	}

	/**
	 * 把字符串转成List，统一保存时使用
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<LinkedHashMap<String, Object>> toList(String str) {
		List<LinkedHashMap<String, Object>> list = null;
		try {
			list = objectMapper.readValue(str, List.class);// 把list对象转成LinkedhashMap,然后根据HashMap来取值
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 字符串返回一个对象，{\"username\":\"张三\"}返回User对象
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object toObject(Class clazz, String str) {
		Object object = null;
		try {
			object = objectMapper.readValue(str, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 生成操作后的json串，{success:false,msgText:'删除失败'}
	 * 
	 * @param flag
	 * @param msg
	 * @return
	 */
	public static String createOperaStr(boolean flag, String msg) {
		return "{success:" + flag + ",msgText:'" + msg + "'}";
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"
				+ "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
		List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) JsonUtil.toObject(List.class, json);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Set<String> set = map.keySet();
			for (Iterator<String> it = set.iterator(); it.hasNext();) {
				String key = it.next();
				System.out.println(key + ":" + map.get(key));
			}
		}
	}

	/**
	 * 创建Extjs分页json
	 * 
	 * @param rows
	 * @param data
	 * @return
	 */
	public static String createExtjsPageJson(long rows, String data) {
		String json = "{\"total\":\"" + rows + "\",\"rows\":" + data + "}";
		return json;
	}

	/**
	 * 创建Extjs分页json
	 * 
	 * @param rows
	 * @param data
	 * @return
	 */
	public static String createExtjsPageJson(long rows, Object obj) {
		String json = "{\"total\":\"" + rows + "\",\"root\":" + toStr(obj) + "}";
		return json;
	}
	
	public static String createEasyUIPageJson(long rows, String data) {
		String json = "{\"total\":\"" + rows + "\",\"rows\":" + data + "}";
		return json;
	}
}
