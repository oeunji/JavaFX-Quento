/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * 게임판 버튼의 종류를 나타내는 열거형
 */
public enum QuentoButtonType {
	NUMBER{
		@Override public String getStyle() {
			return "-fx-background-color: black; -fx-text-fill: white";
		}
	}, 
	OPERATOR{
		@Override public String getStyle() {
			return "-fx-background-color: white; -fx-text-fill: black";
		}
	};
	public abstract String getStyle();
}