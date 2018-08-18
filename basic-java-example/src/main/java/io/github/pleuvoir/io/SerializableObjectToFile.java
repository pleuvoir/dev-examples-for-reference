package io.github.pleuvoir.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * 序列化对象至磁盘，以及反序列化
 * @author pleuvoir
 *
 */
public class SerializableObjectToFile {

	@Data
	@AllArgsConstructor
	public static class Person implements Serializable {
		private static final long serialVersionUID = -1595370086293619106L;
		private String name;
		private Integer age;
	}

	public static Object init() {
		Person younger = new Person("pleuvoir", 18);
		Person older = new Person("pleuvoir", 26);
		List<Person> list = new ArrayList<>();
		list.add(younger);
		list.add(older);
		return list;
	}

	@SneakyThrows
	public static void toFile(File file, Object obj) {
		@Cleanup
		FileOutputStream fos = new FileOutputStream(file);
		@Cleanup
		ObjectOutputStream os = new ObjectOutputStream(fos);
		os.writeObject(obj);
		System.out.println("序列化完成，filepath：" + file);
	}
	
	@SneakyThrows
	public static Object toObject(File file){
		@Cleanup
		FileInputStream fis = new FileInputStream(file);
		@Cleanup
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		return obj;
	}

	public static void main(String[] args) {
		File file = new File("D://序列化.txt");
		toFile(file, init());
		Object object = toObject(file);
		if(object instanceof List){
			@SuppressWarnings("unchecked")
			List<Person> list = (List<Person>) object;
			for (Person person : list) {
				System.out.println(person.toString());
			}
		}
	}

}
