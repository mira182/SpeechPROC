/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.gui.menubar;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

/**
 *
 * @author mira
 */
public class MenuController implements Initializable {

    @FXML
    private MenuItem open;
    @FXML
    private MenuItem close;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void handleOpenAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open sound file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("WAV", "*.wav")
        );

        List<File> list = fileChooser.showOpenMultipleDialog(null);
        if (list != null) {
            list.forEach(file -> {
                openFile(file);
            });
        }
    }

    private void openFile(File file) {

    }

}
