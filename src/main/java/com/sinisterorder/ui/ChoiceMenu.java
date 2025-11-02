package com.sinisterorder.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.sinisterorder.handler.GenericActionHandler;

// Class for handling modular menus
public class ChoiceMenu {
	private String menuId;
	private String menuTitle;
	public Query query;
	private ArrayList<MenuOption> options = new ArrayList<MenuOption>();
	private ArrayList<Label> labels = new ArrayList<Label>();
	static private Scanner scanner = new Scanner(System.in);

	ChoiceMenu(String menuId, String menuTitle) {
		this.menuId = menuId;
		this.menuTitle = menuTitle;
		this.options = new ArrayList<MenuOption>();
		this.labels = new ArrayList<Label>();
	}

	protected void initializeLists() {
		options = new ArrayList<MenuOption>();
		labels = new ArrayList<Label>();
	}

	public void fullWipe() {
		menuTitle = "";
		options.clear();
		labels.clear();
	}

	public void wipeLabels() {
		labels.clear();
	}

	public void wipeOptions() {
		options.clear();
	}

	public void deleteQuery() {
		query = null;
	}

	public String getMenuId() {
		return menuId;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	// Assistant method for MenuFactory
	protected void addOption(MenuOption option) {
		if(this.options == null) {
			this.options = new ArrayList<MenuOption>();
		}

		this.options.add(option);
	}

	// Manual option creation
	public void createOption(String optionId, String optionName, GenericActionHandler action) {
		this.options.add(new MenuOption(optionId, optionName, action));
	}

	// Add executable action to option defined in json
	public void setAction(String optionId, GenericActionHandler action) {

		for (MenuOption menuOption : options) {
			if(menuOption.getOptionId().equals(optionId)) {
				menuOption.setAction(action);
			}
		}
	}

	public void addLabel(String labelId, String text) {
		if(labels == null) {
			labels = new ArrayList<Label>();
		}

		labels.add(new Label(labelId, text));
	}

	// Change text of already existing label
	public void setLabel(String labelId, String text) {

		for (Label label : labels) {
			if(label.getLabelId() == labelId) {
				label.setText(text);
			}
		}
	}

	// Returns list of option names
	// TODO: Would probably be better to return a regular array
	public ArrayList<String> getOptionNames() {
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i = 0; i < options.size(); i++) {
			output.add(options.get(i).getName());
		}

		return output;
	}

	// Execute option code
	protected void runOption(String optionId) {

		for (MenuOption option : options) {
			if(option.getOptionId().equals(optionId)) {
				option.runAction();
			}
		}
	}

	// Display menu without starting user interaction
	public void display() {
		MenuUtils.clear();
		String titleText = String.format("%s  %s  %s", "----====", this.menuTitle, "====----");
		System.out.printf("%s\n", titleText);

		if(labels.size() > 0) {
			MenuUtils.separator();

			for(Label label : labels) {
				System.out.print(label.getText());
			}

			MenuUtils.separator();
			System.out.println();
		}

		// Funny loop for option display formatting
		// Evil i's distant cousin
		for(int i = 0; i < options.size(); i += 2) {
			// Handling for odd amount of options. Should probably rework. A little hacky.
			try {
				System.out.printf("%d) %s\t%d) %s", i + 1, options.get(i).getName(), i + 2, options.get(i+1).getName());
			} catch (Exception e) {
				System.out.printf("%d) %s", i + 1, options.get(i).getName());
			}

			System.out.println();
		}
		
	}

	// Main method for running the menu including user input
	// Validation galore
	public void run() {
		int input = -1;
		boolean ranBefore = false;
		display();

		// Check if input value is in range
		while (input < 0 || input >= options.size()) {

			// Ensure to only output "Invalid input" if this *isn't* the first try to get it, since technically input starts
			// as invalid, to meet validation loop entry condition
			if(ranBefore){
				MenuUtils.clear();
				display();
				System.out.println("Invalid input, try again.");
			}

			// Check if input is int, if not, retry
			while(!scanner.hasNextInt()){
				display();
				System.out.println("Invalid input, try again.");
				scanner.next();
			}

			ranBefore = true; 			// Set ranBefore to true in case user input is out of range
			input = scanner.nextInt() - 1;	// Subtract 1 from user input to make it easier to work with arrays (user indexes from 1, arrays index from 0)
		}

		options.get(input).runAction(); // Run user selected option
	}

	// Add a query, ensure it's properly created
	public void createQuery(String queryId, String queryTitle, String inputMode) {
		try {
			this.query = new Query(queryId, queryTitle, inputMode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add a free mode query
	public void createQuery(String queryId, String queryTitle, String inputMode, int inputRangeStart, int inputRangeEnd) {
		this.query = new Query(queryId, queryTitle, inputMode, inputRangeStart, inputRangeEnd);
	}

	// NOTE: Consider splitting off to separate file 
	// A lot of repeating code with ChoiceMenu itself. Won't go into detail on repeated methods.
	// Class to allow for the user to specify inputs
	public class Query {
		private String queryId;
		private String queryTitle;
		private String inputMode; // TODO: Kinda hacky solution for input mode. Should probably rework.
		private int[] inputRange;
		private ArrayList<MenuOption> suboptions = new ArrayList<MenuOption>();

		Query(String queryId, String queryTitle, String inputMode) throws Exception {
			this.queryId = queryId;
			this.queryTitle = queryTitle;
			this.inputMode = inputMode;
			this.suboptions = new ArrayList<MenuOption>();

			if(inputMode == "free") {
				Exception exception = new Exception("Input mode \"free\" specified yet no range values were given");
				throw exception;
			}
		}
		
		// TODO: Add validation for valid input range
		Query(String queryId, String queryTitle, String inputMode, int inputRangeStart, int inputRangeEnd) {
			this.queryId = queryId;
			this.queryTitle = queryTitle;
			this.inputMode = inputMode;
			this.suboptions = new ArrayList<MenuOption>();

			if(inputMode == "free") {
				inputRange = new int[2];
				inputRange[0] = inputRangeStart;
				inputRange[1] = inputRangeEnd;
			}
		}

		protected void initializeLists() {
			options = new ArrayList<MenuOption>();
		}

		public String getQueryId() {
			return queryId;
		}

		public String getQueryTitle() {
			return queryTitle;
		}

		protected void addOption(MenuOption option) {
			if(this.suboptions == null) {
				this.suboptions = new ArrayList<MenuOption>();
			}

			this.suboptions.add(option);
		}

		public void setAction(String optionId, GenericActionHandler action) {

			for (MenuOption menuOption : suboptions) {
				if(menuOption.getOptionId().equals(optionId)) {
					menuOption.setAction(action);
				}
			}
		}

		public ArrayList<String> getOptionNames() {
			ArrayList<String> output = new ArrayList<String>();
			
			for(int i = 0; i < suboptions.size(); i++) {
				output.add(suboptions.get(i).getName());
			}

			return output;
		}

		public void runOption(String optionId) {

			for (MenuOption option : suboptions) {
				if(option.getOptionId().equals(optionId)) {
					option.runAction();
				}
			}
		}

		// Same display method and formatting as ChoiceMenu except it doesn't clear terminal
		private void displayOptionMode() {
			String titleText = String.format("%s  %s  %s", "--==", this.queryTitle, "==--");
			System.out.printf("%s\n\n", titleText);

			for(int i = 0; i < suboptions.size(); i += 2) {

				try {
					System.out.printf("%d) %s\t%d) %s", i + 1, suboptions.get(i).getName(), i + 2, suboptions.get(i+1).getName());
				} catch (Exception e) {
					System.out.printf("%d) %s", i + 1, suboptions.get(i).getName());
				}

				System.out.println();
			}
		}

		// Displays question and awaits input
		private void displayFreeMode() {
			String query = String.format("%s: ", this.queryTitle);
			System.out.println();
			System.out.print(query);
		}

		// Main query driver code
		//
		// Did somebody say... validation?
		// Oh boy! I love validation!
		public int run() {
			int input = -1;
			boolean ranBefore = false;
			
			// Same validation methods as ChoiceMenu, except there's a branch for free mode and additional checks
			// for correct input range.
			if(inputMode.equals("free")) {
				displayFreeMode();

				while (input > inputRange[1] || input < inputRange[0]) {
					if(ranBefore){
						MenuUtils.clear();
						display();
						System.out.println();
						System.out.println("Invalid input, try again.");
						displayFreeMode();
					}

					while(!scanner.hasNextInt()){
						MenuUtils.clear();
						display();
						System.out.println();
						System.out.println("Invalid input, try again.");
						displayFreeMode();
						scanner.next();
					}

					ranBefore = true;
					input = scanner.nextInt() - 1;
				}
				
				return input;
			} else {
				displayOptionMode();

				while (input < 0 || input >= options.size()) {
					if(ranBefore){
						display();
						displayOptionMode();
						System.out.println("Invalid input, try again.");
					}

					while(!scanner.hasNextInt()){
						displayOptionMode();
						System.out.println("Invalid input, try again.");
						scanner.next();
					}
					
					ranBefore = true;
					input = scanner.nextInt() - 1;
				}

				suboptions.get(input).runAction();
				return 0;
			}
		}
	}
}