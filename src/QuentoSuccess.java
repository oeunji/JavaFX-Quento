import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * 해결한 답 수를 나타내는 노드 
 */
public class QuentoSuccess extends FlowPane {
	private RadioButton[] success = new RadioButton[3];
	public QuentoSuccess() {
		for(int i = 0; i < 3; ++i) {
			success[i] = new RadioButton();
			success[i].setDisable(true);
			success[i].setStyle("-fx-opacity: 1.0");
			getChildren().add(success[i]);
		}
		setAlignment(Pos.CENTER);
		setMaxWidth(100d);
	}
	private void clear() {
		for(int i = 0; i < 3; ++i) {
			success[i].setSelected(false);
		}
	}
	public void set(int count) {
		clear();
		for(int i = 0; i < count; ++i) {
			success[i].setSelected(true);
		}
	}
}
