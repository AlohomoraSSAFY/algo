package study0616;

public class SY_부족한_금액_계산하기 {
    public long solution(int price, int money, int count) {
        long answer = money;
        for (int i = 1; i <= count; i++) {
            answer -= (price * i);
        }
        
        if (answer >= 0) {
            return 0;
        } else {
            return answer * (-1);
        }
    }
}
