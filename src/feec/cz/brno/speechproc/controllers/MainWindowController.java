/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feec.cz.brno.speechproc.controllers;

import java.io.File;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author mira
 */
public class MainWindowController {

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
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("WAV", "*.wav"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );

        List<File> list = fileChooser.showOpenMultipleDialog(null);
        if (list != null) {
            list.forEach(file -> {
                voiceFileList.getItems().add(file);
            });
        }
    }
    
    @FXML
    protected void close() {
        System.exit(0);
    }
    
}
