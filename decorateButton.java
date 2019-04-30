import java.util.Stack;

public class decorateButton extends addressDecorator {

	public decorateButton(addressbookInterface AddressBookPane) {
		// Suppose the addressbookInterface is AddressBookPane so do cast and decorate
		super(AddressBookPane);
		CommandButton add = new AddButton((AddressBookPane) super.address, ((AddressBookPane) super.address).getRaf());
		Stack<CommandButton.Memento> s = new Stack<>();
		CommandButton redo = new RedoBuuton((AddressBookPane) super.address, ((AddressBookPane) super.address).getRaf(),
				s);
		CommandButton undo = new UndoBuuton((AddressBookPane) super.address, ((AddressBookPane) super.address).getRaf(),
				s);
		redo.setOnAction(((AddressBookPane) address).ae);
		undo.setOnAction(((AddressBookPane) address).ae);
		add.setOnAction(((AddressBookPane) address).ae);
		(((AddressBookPane) (super.address)).getJpButton()).getChildren().addAll(add, redo, undo);

	}

	@Override
	public addressbookInterface getAddressBookPane() {
		return super.getAddressBookPane();

	}

}
