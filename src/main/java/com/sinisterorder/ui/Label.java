package com.sinisterorder.ui;

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
