package com.sinisterorder.ui;

// Simple helper class to hold ChoiceMenu labels
// TODO: Can probably change all the methods to protected, should double check later
public class Label {
	private String labelId;
	private String text;

	Label(String labelId, String text) {
		this.labelId = labelId;
		this.text = text;
	}

	public String getLabelId() {
		return labelId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
