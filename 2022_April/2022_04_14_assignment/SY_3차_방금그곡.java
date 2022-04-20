package study0421;

import java.util.*;

public class SY_3차_방금그곡 {
    
    class Music implements Comparable<Music> {
        int num;
        int time;
        String title;
        
        public Music(int num, int time, String title) {
            this.num = num;
            this.time = time;
            this.title = title;
        }
        
        @Override
        public int compareTo(Music o) {
            if (o.time == this.time) {
                return this.num - o.num;
            }
            
            return o.time - this.time;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        Map<String, String> map = new HashMap<>();
        map.put("C#", "H");
        map.put("D#", "I");
        map.put("F#", "J");
        map.put("G#", "K");
        map.put("A#", "L");
        
        for (String s : map.keySet()) {
            m = m.replace(s, map.get(s));
        }
        
        List<Music> list = new ArrayList<>();
        for (int i = 0; i < musicinfos.length; i++) {
            String[] musicinfo = musicinfos[i].split(",");
            String[] startTime = musicinfo[0].split(":");
            String[] endTime = musicinfo[1].split(":");
            int time = (Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0])) * 60 + (Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]));
            int idx = 0;
            String play = "";
            for (String s : map.keySet()) {
                musicinfo[3] = musicinfo[3].replace(s, map.get(s));
            }
            for (int j = 0; j < time; j++) {
                play += Character.toString(musicinfo[3].charAt(idx));
                idx = ++idx % musicinfo[3].length();
            }
            if (play.contains(m)) {
                list.add(new Music(i, time, musicinfo[2]));
            }
        }
        
        String answer = "(None)";
        Collections.sort(list);
        if (list.size() != 0) {
            answer = list.get(0).title;
        }
        return answer;
    }
}
