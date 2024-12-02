package animations;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class HighlightingSmall2 {
    private FadeTransition ft;

    public HighlightingSmall2(Button button) {
        ft = new FadeTransition(Duration.millis(100), button);
        ft.setFromValue(1.0);
        ft.setToValue(1.0);

        button.setOnMouseEntered(event -> {
            ft.playFromStart();
            button.setStyle("-fx-background-color: #43484f;");
        });

        button.setOnMouseExited(event -> {
            ft.stop();
            button.setStyle("-fx-background-color: #23272A;");
        });
    }
}