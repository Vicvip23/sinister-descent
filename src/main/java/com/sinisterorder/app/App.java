package com.sinisterorder.app;

import com.google.gson.Gson;
import com.sinisterorder.item.itemreader.ItemReader;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ItemReader itemReader=new ItemReader();

		System.out.println(itemReader.itemFactory("itemID"));
	}
}
