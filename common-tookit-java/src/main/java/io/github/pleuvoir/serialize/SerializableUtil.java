package io.github.pleuvoir.serialize;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * 使用protostuff进行对象的序列化和反序列化
 * @author pleuvoir
 *
 */
@SuppressWarnings("unchecked")
public class SerializableUtil {

	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

	/**
	 * 序列化
	 */
	public static <T> byte[] serialize(T obj){
		Class<T> cls = (Class<T>) obj.getClass();
	    LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
	    try {
	        Schema<T> schema = getSchema(cls);
	        return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
	    } finally {
	        buffer.clear();
	    }
	}
	
	/**
	 * 反序列化
	 */
	public static <T> T deserialize(byte[] data, Class<T> cls) {
		try {
			T message = cls.newInstance();
			Schema<T> schema = getSchema(cls);
			ProtostuffIOUtil.mergeFrom(data, message, schema);
			return message;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
	
	private static <T> Schema<T> getSchema(Class<T> cls) {
	    Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
	    if (schema == null) {
	        schema = RuntimeSchema.createFrom(cls);
	        if (schema != null) {
	            cachedSchema.put(cls, schema);
	        }
	    }
	    return schema;
	}
	
}
