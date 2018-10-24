/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.xml.bind.JAXBException;

/**
 * FXML Controller class
 *
 * @author Tomás
 */
public class PantallaInicioController implements Initializable {

    @FXML
    private Button iniciar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrar_pant_juego(ActionEvent event) throws Exception {
        InterfazJuego accionar = new InterfazJuego();
        Stage cierre = (Stage) iniciar.getScene().getWindow();
        /*
        cierre.setOnCloseRequest((WindowEvent event1) -> {
            try {
                InterfazJuego.finish();
            } catch (JAXBException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });*/
        accionar.start(cierre);
        cierre.close();
        
    }
    
}
