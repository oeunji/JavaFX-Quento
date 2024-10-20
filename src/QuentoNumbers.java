import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * 선택한 숫자 수를 나타내는 노드 
 */
public class QuentoNumbers extends FlowPane {
	private CheckBox[] checks;
	public QuentoNumbers(int n) {
		if(n < 2 || n > 5) throw new IllegalArgumentException();
		checks = new CheckBox[n];
		for(int i = 0; i < n; ++i) {
			checks[i] = new CheckBox();
			checks[i].setStyle("-fx-mark-color: blue; -fx-opacity: 1.0");
			checks[i].setDisable(true);
			getChildren().addAll(checks[i]);
			
		}
		setAlignment(Pos.CENTER);
		setMaxWidth(100d);
	}
	
	private void clear() {
		for(int i = 0; i < checks.length; ++i) {
			checks[i].setSelected(false);
		}
	}
	
	public void set(int count) {
		clear();
		for(int i = 0; i < count; ++i) {
			checks[i].setSelected(true);
		}
	}
}
