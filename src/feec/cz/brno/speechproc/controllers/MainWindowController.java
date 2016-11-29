/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.controllers;

import feec.cz.brno.speechproc.calc.PraatFunctions;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * FXML Controller class
 *
 * @author mira
 */
public class MainWindowController {
    
    private final static Logger logger = LogManager.getLogger(MainWindowController.class);

    @FXML
    private ListView<File> voiceFileList;

    @FXML
    public void initialize() {
        voiceFileList.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {

            @Override
            public ListCell<File> call(ListView<File> param) {
                ListCell<File> cell = new ListCell<File>() {

                    @Override
                    protected void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName() + "\t" + item.length()/1024 + " KB");
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });
    }
    
    @FXML
    protected void addVoiceFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open sound file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("WAV", "*.wav"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        List<File> list = fileChooser.showOpenMultipleDialog(null);
        if (list != null) {
            list.forEach(file -> {
                logger.debug("Opening sound file: {}", file.getAbsoluteFile());
                voiceFileList.getItems().add(file);
            });
        }
    }
    
    @FXML
    protected void runPraatScript() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open praat script");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Praat script", "*.praat"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File praatScript = fileChooser.showOpenDialog(null);
        if (praatScript != null) {
            List<String> parameters = new ArrayList<>();
            File selectedFile = voiceFileList.getSelectionModel().getSelectedItems().get(0);
            parameters.add(selectedFile.getAbsolutePath().replaceFirst(selectedFile.getName(), "test.csv"));
            PraatFunctions praat = new PraatFunctions(praatScript, parameters);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Praat script has finished successfully.");

            alert.showAndWait();
        }
    }
    
    @FXML
    protected void close() {
        System.exit(0);
    }
    
}
