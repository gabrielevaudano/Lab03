package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> dropDown;

    @FXML
    private TextArea msgSpell;

    @FXML
    private Button btnCheckSpell;

    @FXML
    private TextArea msgWrongWord;

    @FXML
    private Button btnClear;

    @FXML
    private Label errorNumbers;

    @FXML
    private Label executionTime;
    
	private Model model;

    @FXML
    void doClear(ActionEvent event) {
    	msgWrongWord.clear();
    }
    
    @FXML
    public void initialize() {
    	dropDown.getItems().removeAll(dropDown.getItems());
    	dropDown.getItems().addAll("Italian", "English");
    	dropDown.getSelectionModel().select("Italian");
    }

    @FXML
    void doSpell(ActionEvent event) {
    	Long inizio = System.currentTimeMillis();
    	// Watch comments on Model class to understand next instruction
    	msgWrongWord.setText(model.toString(model.spellCheckText(dropDown.getSelectionModel().getSelectedItem(), msgSpell.getText())));       	
       	Long fine = System.currentTimeMillis();

    	errorNumbers.setText(model.getWrongWords());
       	
       	executionTime.setText("Spell Check completed in " + (fine-inizio) + " ms");
    }

	public void setModel(Model model) {
		this.model = model;
	}    
}
