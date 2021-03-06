package me.coley.recaf.ui.controls;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import me.coley.recaf.config.ConfKeybinding;
import me.coley.recaf.control.gui.GuiController;

import java.util.function.Predicate;

/**
 * TextField with an on-save action.
 *
 * @author Matt
 */
public final class ActionTextField extends TextField {
	private final GuiController controller;
	private final Predicate<String> action;

	/**
	 * @param controller
	 * 		Controller to pull save keybind from.
	 * @param text
	 * 		Initial text.
	 * @param action
	 * 		Action to run on-save.
	 */
	public ActionTextField(GuiController controller, String text, Predicate<String> action) {
		super(text == null ? "" : text);
		this.controller = controller;
		this.action = action;
		setOnKeyReleased(this::handleKeyReleased);
	}

	private void handleKeyReleased(KeyEvent e) {
		ConfKeybinding keys = controller.config().keys();
		if (keys.save.match(e)) {
			action.test(getText());
		}
	}

	/**
	 * @return On-save action.
	 */
	public Predicate<String> getAction() {
		return action;
	}
}