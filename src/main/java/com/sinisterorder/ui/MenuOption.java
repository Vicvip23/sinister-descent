package com.sinisterorder.ui;

import com.sinisterorder.handler.GenericActionHandler;

public class MenuOption {
	private String optionId;
	private String optionName;
	private GenericActionHandler action;

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

	// Pass lambda as action to execute when option is chosen
	public void setAction(GenericActionHandler action) {
		this.action = action;
	}

	// Pass lambda as action to execute when option is chosen and run it instantly
	public void setAndRun(GenericActionHandler action) {
		this.action = action;
		this.action.action();
	}

	// Run saved lambda
	public void runAction(){
		this.action.action();
	};
}
