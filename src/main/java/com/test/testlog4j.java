package com.test;

import org.apache.log4j.Logger;
 
public class testlog4j {
 private static final Logger LOG = Logger.getLogger(testlog4j.class);
	public static void main(String[] args){
		 LOG.info("this is log");
	}
}
