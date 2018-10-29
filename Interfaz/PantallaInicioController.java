
package Interfaz;

import static Interfaz.InterfazInicio.player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
/**
 * Cuando se oprime el boton se despliega una pantalla de juego
 */
    private void mostrar_pant_juego(ActionEvent event) throws Exception {

        InterfazJuego accionar = new InterfazJuego();
        player.stop();
        accionar.start(new Stage());
        Stage cierre = (Stage) iniciar.getScene().getWindow();
        cierre.close();
        
    }
    
}
