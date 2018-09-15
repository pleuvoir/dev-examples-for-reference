package io.github.pleuvoir.model;

import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author pleuvoir
 * 
 */
@Data
@RequiredArgsConstructor
public class Select {

	private @NonNull List<Option> options;
	
	
}
