package com.muppet.auth;

public class Test {

	public  static void main(String[] args){
		Exception e = new Exception("Exception ");
		ExceptionTest test = new ExceptionTest("hi", e);
		test.printStackTrace();
		test.getCause().toString();
	}
}
