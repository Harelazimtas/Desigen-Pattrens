import javafx.scene.layout.GridPane;

public abstract class addressDecorator extends GridPane implements addressbookInterface {
	public addressbookInterface address;

	public addressDecorator(addressbookInterface address) {
		this.address = address;
	}

	@Override
	public addressbookInterface getAddressBookPane() {
		return address.getAddressBookPane();
	}

}
