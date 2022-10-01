package com.example.reflection.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.reflection.annotation.annotations.InitializeMethod;
import com.example.reflection.annotation.annotations.InitializerClass;
import com.example.reflection.annotation.annotations.RetryOperation;
import com.example.reflection.annotation.annotations.ScanPackages;

/**
 * 중요하게 봐야할 점
 * - Classes Discovery
 * - annotation 찾기
 */
@ScanPackages({"app", "app.configs", "app.databases", "app.http"})
public class Main {
	public static void main(String[] args) throws Throwable {
		initialize();
	}

	public static void initialize() throws Throwable {
		// @ScanPackages에 입력되어 있는 패키지 찾
		ScanPackages scanPackages = Main.class.getAnnotation(ScanPackages.class);

		if (scanPackages == null || scanPackages.value().length == 0) {
			return;
		}

		List<Class<?>> classes = getAllClasses(scanPackages.value());

		for (Class<?> clazz : classes) {
			if (!clazz.isAnnotationPresent(InitializerClass.class)) {
			}

			List<Method> methods = getAllInitializingMethods(clazz);

			Object instance = clazz.getDeclaredConstructor().newInstance();

			for (Method method : methods) {
				callInitializingMethod(instance, method);
			}
		}
	}

	private static void callInitializingMethod(Object instance, Method method) throws Throwable {
		RetryOperation retryOperation = method.getAnnotation(RetryOperation.class);

		int numberOfRetries = retryOperation == null ? 0 : retryOperation.numberOfRetries();

		while (true) {
			try {
				method.invoke(instance);
				break;
			} catch (InvocationTargetException e) {
				Throwable targetException = e.getTargetException();

				if (numberOfRetries > 0 && Set.of(retryOperation.retryException())
					.contains(targetException.getClass())) {
					numberOfRetries--;

					System.out.println("Retrying...");
					Thread.sleep(retryOperation.durationBetweenRetriesMs());
				} else if (retryOperation != null) {
					throw new Exception(retryOperation.failureMessage(), targetException);
				} else {
					throw targetException;
				}
			}
		}
	}

	private static List<Method> getAllInitializingMethods(Class<?> clazz) {
		List<Method> initializingMethods = new ArrayList<>();

		for (Method method : clazz.getDeclaredMethods()) {
			if (method.isAnnotationPresent(InitializeMethod.class)) {
				initializingMethods.add(method);
			}
		}

		return initializingMethods;
	}

	public static List<Class<?>> getAllClasses(String... packageNames) throws
		URISyntaxException,
		IOException,
		ClassNotFoundException {
		List<Class<?>> allClasses = new ArrayList<>();

		for (String packageName : packageNames) {
			// Step 1 : Convert package name “app.data” → “app/data”
			String packageRelativePath = packageName.replace('.', '/');
			// Step 2 : Use getResource(..) method of a Class<?> or ClassLoader to get the URI(Uniform Resource Identifier) of “app/data”
			URI packageUri = Main.class.getResource(packageRelativePath).toURI();

			// Step 3 : Get the Path of the package directory “app/data” → “/home/additional_classes/app/data/”
			if (packageUri.getScheme().equals("file")) {
				Path packageFullPath = Paths.get(packageUri);
				allClasses.addAll(getAllPackageClasses(packageFullPath, packageName));
			} else if (packageUri.getScheme().equals("jar")) {
				FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());

				Path packageFullpathInJar = fileSystem.getPath(packageRelativePath);
				allClasses.addAll(getAllPackageClasses(packageFullpathInJar, packageName));

				fileSystem.close();
			}

		}
		return allClasses;
	}

	private static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName) throws
		IOException,
		ClassNotFoundException {

		// 경로가 존재하는지 확인
		if (!Files.exists(packagePath)) {
			return Collections.emptyList();
		}

		// 경로가 있다면 모든 레귤러 파일을 찾도록 한다.
		List<Path> files = Files.list(packagePath)
			.filter(Files::isRegularFile)
			.collect(Collectors.toList());

		List<Class<?>> classes = new ArrayList<>();

		for (Path filePath : files) {
			String fileName = filePath.getFileName().toString();

			if (fileName.endsWith(".class")) {
				// 클래스 파일에서 이름 추출
				String classFullName = packageName + "." + fileName.replaceFirst("\\.class$", "");
				Class<?> clazz = Class.forName(classFullName);
				classes.add(clazz);
			}
		}

		return classes;
	}
}
