import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

// name:Harel Azim Tas id:313320228
public class HW2_HarelAzimTas extends Application implements addressbookFinal {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		addressbookInterface[] pane = new addressbookInterface[NUMBER_OF_OBJECTS];
		Scene[] scene = new Scene[NUMBER_OF_OBJECTS];
		Stage[] stages = new Stage[NUMBER_OF_OBJECTS];
		for (int i = 0; i < NUMBER_OF_OBJECTS+1 ; i++) {
			if (i >= NUMBER_OF_OBJECTS)
			{
				System.out.println(SINGLETON_MESSAGE);
				break;
			}

			if (i == NUMBER_OF_OBJECTS - 1)
				pane[i] = new decorateButton(AddressBookPane.getInstance());
			else
				pane[i] = AddressBookPane.getInstance();
			// the addressbookInterface is don't extend gridPane so i do cast
			scene[i] = new Scene((Parent) pane[i].getAddressBookPane());
			scene[i].getStylesheets().add("styles.css");
			stages[i] = new Stage();
			stages[i].setTitle("AddressBook");
			stages[i].setScene(scene[i]);
			stages[i].setResizable(true);
			stages[i].show();
			stages[i].setAlwaysOnTop(true);

		}

	}
}

class AddressBookPane extends GridPane implements addressbookInterface, addressbookFinal {
	private RandomAccessFile raf;
	// singalton
	private static int number_of_objects = 0;
	// Text fields
	private TextField jtfName = new TextField();
	private TextField jtfStreet = new TextField();
	private TextField jtfCity = new TextField();
	private TextField jtfState = new TextField();
	private TextField jtfZip = new TextField();
	// Buttons
	private FlowPane jpButton;
	private FirstButton jbtFirst;
	private NextButton jbtNext;
	private PreviousButton jbtPrevious;
	private LastButton jbtLast;
	public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			((Command) arg0.getSource()).Execute();
		}
	};

	private AddressBookPane() { // Open or create a random access file
		try {
			raf = new RandomAccessFile("address.dat", "rw");
		} catch (IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}
		jtfState.setAlignment(Pos.CENTER_LEFT);
		jtfState.setPrefWidth(25);
		jtfZip.setPrefWidth(60);
		jbtFirst = new FirstButton(this, raf);
		jbtNext = new NextButton(this, raf);
		jbtPrevious = new PreviousButton(this, raf);
		jbtLast = new LastButton(this, raf);
		Label state = new Label("State");
		Label zp = new Label("Zip");
		Label name = new Label("Name");
		Label street = new Label("Street");
		Label city = new Label("City");
		// Panel p1 for holding labels Name, Street, and City
		GridPane p1 = new GridPane();
		p1.add(name, 0, 0);
		p1.add(street, 0, 1);
		p1.add(city, 0, 2);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(name, Priority.ALWAYS);
		GridPane.setVgrow(street, Priority.ALWAYS);
		GridPane.setVgrow(city, Priority.ALWAYS);
		// City Row
		GridPane adP = new GridPane();
		adP.add(jtfCity, 0, 0);
		adP.add(state, 1, 0);
		adP.add(jtfState, 2, 0);
		adP.add(zp, 3, 0);
		adP.add(jtfZip, 4, 0);
		adP.setAlignment(Pos.CENTER_LEFT);
		GridPane.setHgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfState, Priority.ALWAYS);
		GridPane.setVgrow(jtfZip, Priority.ALWAYS);
		GridPane.setVgrow(state, Priority.ALWAYS);
		GridPane.setVgrow(zp, Priority.ALWAYS);
		// Panel p4 for holding jtfName, jtfStreet, and p3
		GridPane p4 = new GridPane();
		p4.add(jtfName, 0, 0);
		p4.add(jtfStreet, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfName, Priority.ALWAYS);
		GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfName, Priority.ALWAYS);
		GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		// Place p1 and p4 into jpAddress
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		// Set the panel with line border
		jpAddress.setStyle("-fx-border-color: grey;" + " -fx-border-width: 1;" + " -fx-border-style: solid outside ;");
		// Add buttons to a panel
		jpButton = new FlowPane();
		jpButton.setHgap(5);
		jpButton.getChildren().addAll(jbtFirst, jbtNext, jbtPrevious, jbtLast);
		jpButton.setAlignment(Pos.CENTER);
		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		// Add jpAddress and jpButton to the stage
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);
		jbtFirst.setOnAction(ae);
		jbtNext.setOnAction(ae);
		jbtPrevious.setOnAction(ae);
		jbtLast.setOnAction(ae);
		jbtFirst.Execute();
	}

	// for get flowPane of button and get access to decorate
	public FlowPane getJpButton() {
		return jpButton;
	}

	public void actionHandled(ActionEvent e) {
		((Command) e.getSource()).Execute();
	}

	public void SetName(String text) {
		jtfName.setText(text);
	}

	public void SetStreet(String text) {
		jtfStreet.setText(text);
	}

	public void SetCity(String text) {
		jtfCity.setText(text);
	}

	public void SetState(String text) {
		jtfState.setText(text);
	}

	public void SetZip(String text) {
		jtfZip.setText(text);
	}

	public String GetName() {
		return jtfName.getText();
	}

	public String GetStreet() {
		return jtfStreet.getText();
	}

	public String GetCity() {
		return jtfCity.getText();
	}

	public String GetState() {
		return jtfState.getText();
	}

	public String GetZip() {
		return jtfZip.getText();
	}

	// get raf for decorate
	public RandomAccessFile getRaf() {
		return raf;
	}

	public static AddressBookPane getInstance() {
		if (number_of_objects > NUMBER_OF_OBJECTS)
			return null;
		else {
			number_of_objects++;
			return new AddressBookPane();
		}
	}

	public static void reduceNumberOfObjects() {
		number_of_objects--;
	}

	public static int getNumberOfObjects() {
		return number_of_objects;
	}

	public static void resetNumberOfObjects() {
		number_of_objects = 0;
	}

	// this Method is decorate basic
	@Override
	public addressbookInterface getAddressBookPane() {
		return this;
	}

}

interface Command {
	public void Execute();
}

class CommandButton extends Button implements Command, addressbookFinal {

	protected AddressBookPane p;
	protected RandomAccessFile raf;

	public CommandButton(AddressBookPane pane, RandomAccessFile r) {
		super();
		p = pane;
		raf = r;
	}

	public void Execute() {
	}

	/** Write a record at the end of the file */
	public void writeAddress() {
		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(p.GetName(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetStreet(), STREET_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetCity(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetState(), STATE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetZip(), ZIP_SIZE, raf);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Read a record at the specified position */
	public void readAddress(long position) throws IOException {

		raf.seek(position);
		String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
		String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
		String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
		String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
		p.SetName(name);
		p.SetStreet(street);
		p.SetCity(city);
		p.SetState(state);
		p.SetZip(zip);
	}

	public void setAddress(String name, String street, String city, String state, String zip) {
		p.SetName(name);
		p.SetStreet(street);
		p.SetCity(city);
		p.SetState(state);
		p.SetZip(zip);
	}

	// get string from file with position
	public String getAddress(long pos) throws IOException {
		raf.seek(pos);
		String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
		String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
		String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
		String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
		return name + street + city + state + zip;

	}

	public void writeStringFromMemento(Memento m) throws IOException {
		raf.seek(raf.length());
		FixedLengthStringIO.writeFixedLengthString(m.state, RECORD_SIZE, raf);
	}

	public static class Memento {
		private String state;

		protected Memento(String state) {
			this.state = state;
		}

		@SuppressWarnings("unused")
		private String getState() {
			return state;
		}

	}

}

class NextButton extends CommandButton {
	public NextButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Next");
	}

	@Override
	public void Execute() {
		try {
			long currentPosition = raf.getFilePointer();
			if (currentPosition < raf.length())
				readAddress(currentPosition);
			else if (raf.length() == 0) {
				// if the length is zero move the file pointer to begin and refresh
				setAddress("", "", "", "", "");
				raf.seek(0);
			} else {
				// if i do undo and the filePointer > raf.length the next will be the last and
				// refresh
				raf.seek(raf.length() - RECORD_SIZE * 2);
				readAddress(raf.length() - RECORD_SIZE * 2);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class PreviousButton extends CommandButton {
	public PreviousButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Previous");
	}

	@Override
	public void Execute() {
		try {
			// if i do other screen next/last and after that i click undo so move the
			// file pointer
			if (raf.length() == 0)
				raf.seek(0);
			long currentPosition = raf.getFilePointer();
			if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
				readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
			// refresh
			else if (raf.length() == 0)
				setAddress("", "", "", "", "");
		} catch (IOException ex) {
			System.err.println("can suppsoe  after click undo can only click :next,first,last in the other screen");
			ex.printStackTrace();
		}
	}
}

class LastButton extends CommandButton {
	public LastButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Last");
	}

	@Override
	public void Execute() {
		try {
			long lastPosition = raf.length();
			if (lastPosition > 0)
				readAddress(lastPosition - 2 * RECORD_SIZE);
			else {
				// refresh
				setAddress("", "", "", "", "");
				raf.seek(0);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class FirstButton extends CommandButton {
	public FirstButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("First");
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() > 0)
				readAddress(0);
			else {
				// refresh
				setAddress("", "", "", "", "");
				raf.seek(0);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class AddButton extends CommandButton {
	public AddButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Add");
	}

	@Override
	public void Execute() {
		writeAddress();
	}

}

class RedoBuuton extends CommandButton {
	private Stack<Memento> stackRedo;

	public RedoBuuton(AddressBookPane pane, RandomAccessFile r, Stack<Memento> s) {
		super(pane, r);
		this.setText("redo");
		this.stackRedo = s;
	}

	@Override
	public void Execute() {

		try {

			if (!stackRedo.isEmpty()) {
				writeStringFromMemento(stackRedo.pop());
				readAddress(raf.length() - (2 * RECORD_SIZE));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

class UndoBuuton extends CommandButton {
	private Stack<Memento> stackundo;

	public UndoBuuton(AddressBookPane pane, RandomAccessFile r, Stack<Memento> s) {
		super(pane, r);
		this.setText("undo");
		this.stackundo = s;
	}

	@Override
	public void Execute() {
		try {

			long lastPos = raf.length();
			if (lastPos > 0) {
				stackundo.push(new Memento(getAddress(lastPos - (2 * RECORD_SIZE))));
				raf.setLength(lastPos - (2 * RECORD_SIZE));
				if (raf.length() > 0) {
					readAddress(0);
				} else {
					// refresh
					setAddress("", "", "", "", "");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
