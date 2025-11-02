package com.sinisterorder.handler;

// Makes MenuOption magic work.
// Allow for passing lambdas as method parameters
@FunctionalInterface
public interface GenericActionHandler {

	public void action();
	
}