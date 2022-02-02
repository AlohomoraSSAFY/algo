package study0203;

public class SY_1차_추석_트래픽 {
    public int solution(String[] lines) {
        double[][] logArray = new double[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String[] sArray = lines[i].split(" ");
            String[] endTimeArray = sArray[1].split(":");
            String time = sArray[2].substring(0, sArray[2].length() - 1);
            
            double sum = 0;
            sum += (Double.parseDouble(endTimeArray[0]) * 3600);
            sum += (Double.parseDouble(endTimeArray[1]) * 60);
            sum += (Double.parseDouble(endTimeArray[2]));
            logArray[i][1] = sum;
            logArray[i][0] = sum - Double.parseDouble(time) + 0.001;
        }
        
        int answer = 0;
        for (int i = 0; i < logArray.length; i++) {
            int cnt = 1;
            for (int j = i + 1; j < logArray.length; j++) {
                if (logArray[j][0] < logArray[i][1] + 1) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
}
