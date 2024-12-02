package animations;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonPressAnimation {
    private ScaleTransition st;

    public ButtonPressAnimation(Button button1, Button button2) {
        st = new ScaleTransition(Duration.millis(100), button1);
        st = new ScaleTransition(Duration.millis(100), button2);
        st.setToX(1.0);
        st.setToY(1.0);

        button1.setOnMousePressed(event -> {
            st.playFromStart();
        });

        button1.setOnMouseReleased(event -> {
            st.stop();
            button1.setScaleX(1.0);
            button1.setScaleY(1.0);
            button1.setStyle("-fx-background-color: #7289DA;");
            button2.setStyle("-fx-background-color: #2C2F33;-fx-border-color: #7289DA;-fx-border-radius: 5;");
        });
    }
}
