package com.sinisterorder.ui;

import com.sinisterorder.handler.GenericActionHandler;

// Main focus of menu design
// Option class that allows for passing custom code snippets to run on demand
public class MenuOption {
	private String optionId;
	private String optionName;
	private GenericActionHandler action; // Parameter to hold lambda expression

	protected MenuOption(String optionId, String optionName, GenericActionHandler action) {
		this.optionId = optionId;
		this.optionName = optionName;
		this.action = action;
	}

	public String getOptionId() {
		return optionId;
	}

	public String getName() {
		return optionName;
	}

	// Pass lambda as action to execute when called
	public void setAction(GenericActionHandler action) {
		this.action = action;
	}

	// NOTE: probably safe to delete
	// Pass lambda as action to execute when option is chosen and run it instantly
	public void setAndRun(GenericActionHandler action) {
		this.action = action;
		this.action.action();
	}

	// Run saved lambda
	public void runAction() {
		this.action.action();
	};
}
