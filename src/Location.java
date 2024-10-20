/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 자바프로그래밍
 * @version 2023년도 2학기
 * @author 김상진
 * 학기 프로젝트: Quento
 * 셀 위치 정보
 */
public record Location(int r, int c){
	public boolean valid(){
		return (r >=0 && r<3 && c >=0 && c<3);
	}
}
