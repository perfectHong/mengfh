package jvm.u8c.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件系统类加载器
 * @author zhangjxh
 *
 */
public class FileSystemClassLoader extends ClassLoader {
	private String rootDir;

	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}

//	@Override
//	public Class<?> loadClass(String name) throws ClassNotFoundException {
//		return findClass(name);
//	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("FileSystemClassLoader load class"+name);
		byte[] classData = getClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] getClassData(String className) {
		String path = classNameToPath(className);
		try {
			InputStream ins = new FileInputStream(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String classNameToPath(String className) {
		return rootDir + File.separatorChar
				+ className.replace('.', File.separatorChar) + ".class";
	}
}
