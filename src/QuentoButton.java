import javafx.scene.control.Button;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * 게임판 버튼
 */
public class QuentoButton extends Button {
	public final int r;
	public final int c;
	public final QuentoButtonType buttonType;
	public QuentoButton(int r, int c) {
		this.r = r;
		this.c = c;
		buttonType = Math.abs(r - c) == 1? QuentoButtonType.OPERATOR: QuentoButtonType.NUMBER;
		setStyle(buttonType.getStyle());
	}
	
	public void select() {
		setStyle("-fx-background-color: yellow; -fx-text-fill: black");
	}
	
	public void deSelect() {
		setStyle(buttonType.getStyle());
	}
}
