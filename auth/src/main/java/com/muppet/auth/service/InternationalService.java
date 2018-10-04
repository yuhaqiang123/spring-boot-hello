package com.muppet.auth.service;

import java.util.Locale;

public interface InternationalService {
	
	public String getMessage(String code, Object[] params, Locale locale);
	
	public String getMessage(String code, Locale locale);
	
}
