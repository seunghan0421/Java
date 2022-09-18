package com.example.reflection.introduction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PopupTypeInfo {

	private boolean isPrimitive;
	private boolean isInterface;
	private boolean isEnum;

	private String name;
	private boolean isJdk;

	private final List<String> inheritedClassNames = new ArrayList<>();

	public PopupTypeInfo setPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
		return this;
	}

	public PopupTypeInfo setInterface(boolean isInterface) {
		this.isInterface = isInterface;
		return this;
	}

	public PopupTypeInfo setEnum(boolean isEnum) {
		this.isEnum = isEnum;
		return this;
	}

	public PopupTypeInfo setName(String name) {
		this.name = name;
		return this;
	}

	public PopupTypeInfo setJdk(boolean isJdk) {
		this.isJdk = isJdk;
		return this;
	}

	public PopupTypeInfo addAllInheritedClassNames(String[] inheritedClassNames) {
		if (inheritedClassNames != null) {
			this.inheritedClassNames.addAll(Arrays.stream(inheritedClassNames)
				.collect(Collectors.toList()));
		}

		return this;
	}

	public boolean isPrimitive() {
		return isPrimitive;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public boolean isEnum() {
		return isEnum;
	}

	public String getName() {
		return name;
	}

	public boolean isJdk() {
		return isJdk;
	}

	public List<String> getInheritedClassNames() {
		return Collections.unmodifiableList(inheritedClassNames);
	}

	@Override
	public String toString() {
		return "PopupTypeInfo{" +
			"isPrimitive=" + isPrimitive +
			", isInterface=" + isInterface +
			", isEnum=" + isEnum +
			", name='" + name + '\'' +
			", isJdk=" + isJdk +
			", inheritedClassNames=" + inheritedClassNames +
			'}';
	}
}
