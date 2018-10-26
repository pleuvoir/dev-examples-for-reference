package io.github.pleuvoir.chapter7.location;

import java.io.IOException;

public interface AppInfoService {

	void setLocation(String location);

	String getLocation();

	void show() throws IOException;
}
