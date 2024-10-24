package ch1;

/*변수
 * 1) 멤버 변수 : 클래스 안에 선언된 변수
 *                객체인 경우 null로 초기화 됨
 *                기본 타입 변수(int,boolean..)인 경우 0 or '' or 0.0, false 초기화 됨
 * 
 * 2) 지역 변수 : 메소드나 {} 안에 선언된 변수
 * 3) 매개 변수 : powerOn(int x) => x는 매개변수
 */
public class LgTV implements TV {

    // 멤버 변수(== 인스턴스 변수, 필드, 특성)
    private Speaker speaker;

    // 생성자 만들거나
    // public LgTV(BritzSpeaker britzSpeaker) {
    // this.britzSpeaker = britzSpeaker;
    // }

    // setter 만들거나
    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void powerOn() {
        System.out.println("LgTV - 전원 On");
    }

    @Override
    public void powerOff() {
        System.out.println("LgTV - 전원 Off");
    }

    @Override
    public void volumeUp() {
        // System.out.println("LgTV - 볼륨 Up");
        speaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        // System.out.println("LgTV - 볼륨 Down");
        speaker.volumeDown();
    }

}
