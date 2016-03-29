package demo.neo4j.jcypher.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ConsoleUtil {

  private static Logger logger = LoggerFactory.getLogger(ConsoleUtil.class);

  /**
   * 将POJO对象打印成json字符串
   * 
   * @param object
   */
  public static void ConsoleObject(Object object) {
	String jsonStr = null;
	try {
	  ObjectMapper objectMapper = getObjectMapperWithNull();
	  jsonStr = objectMapper.writeValueAsString(object);
	  logger.debug("输出Json日志:{}", jsonStr);
	} catch (Exception e) {
	  logger.error(e.toString());
	}
  }

  /***
   * 保留空值的ObjectMapper
   * 
   * @return ObjectMapper
   */
  public static ObjectMapper getObjectMapperWithNull() {
	ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	objectMapper.setSerializationInclusion(Include.ALWAYS);
	// 去除不存在的属性
	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	// 空字符串转换null对象
	objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	// 不去除值为null的值
	objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
	// 默认时间戳改成自定义日期格式
	objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	objectMapper.setDateFormat(dFormat);
	return objectMapper;
  }

  /**
   * 将POJO对象打印成json字符串
   * 
   * @param object
   */
  public static void ConsoleObject(Object object, boolean isUserAnnotations) {
	String jsonStr = null;
	try {
	  ObjectMapper objectMapper = getObjectMapperWithNull();
	  objectMapper.configure(MapperFeature.USE_ANNOTATIONS, isUserAnnotations);
	  jsonStr = objectMapper.writeValueAsString(object);
	  logger.debug("输出Json日志:{}", jsonStr);
	} catch (Exception e) {
	  logger.error(e.toString());
	}
  }
}
