/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.IOException;
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
    private void mostrar_pant_juego(ActionEvent event) throws IOException {
        
        Parent inicio = FXMLLoader.load(getClass().getResource("PantallaJuego.fxml"));
         Scene juego = new Scene(inicio);
         Stage vent = new Stage();
         vent.setResizable(false); 
         vent.setScene(juego);
         vent.show();
         Stage cierre = (Stage) iniciar.getScene().getWindow();
        cierre.close();
        
    }
    
}
