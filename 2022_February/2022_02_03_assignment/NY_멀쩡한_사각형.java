package date0203;

public class NY_멀쩡한_사각형 {
	public long solution(int w, int h) {
        long answer = 1;
        
        if(w == 1 || h == 1)
            return 0;
        if(w == h )
            return w*h - w;
        
        long a = Math.max(w,h);
        long b = Math.min(w,h);
        
        while(b != 0){
            long temp = a % b;
            a = b;
            b = temp;
        }
        
        long gcd = a;
        return ((long)w*(long)h) - (((long)w/gcd+ (long)h/gcd -1 )*gcd);
    }
}
