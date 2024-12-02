package animations;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class HighlightingBig {
    private FadeTransition ft;

    public HighlightingBig(Button button) {
        ft = new FadeTransition(Duration.millis(100), button);
        ft.setFromValue(1.0);
        ft.setToValue(1.0);

        button.setOnMouseEntered(event -> {
            ft.playFromStart();
            button.setStyle("-fx-background-color: #9cace6;");
        });

        button.setOnMouseExited(event -> {
            ft.stop();
            button.setStyle("-fx-background-color: #7289DA;");
        });
    }
}