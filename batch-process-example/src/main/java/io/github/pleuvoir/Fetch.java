package io.github.pleuvoir;

import java.util.concurrent.Future;

public interface Fetch {

	Object fetch();

	Future<Object> fetchAsync();

}
