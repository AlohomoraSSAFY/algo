package study0106;

import java.util.*;

public class SY_베스트앨범 {
    class Music implements Comparable<Music> {
        int index;
        String genre;
        int genreCnt;
        int play;
        
        public Music(int index, String genre, int genreCnt, int play) {
            this.index = index;
            this.genre = genre;
            this.genreCnt = genreCnt;
            this.play = play;
        }
        
        @Override
        public int compareTo(Music o) {
            if (this.genreCnt == o.genreCnt) {
                if (this.play == o.play) {
                    return this.index - o.index;
                }
                
                return o.play - this.play;
            }
            
            return o.genreCnt - this.genreCnt;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        List<Music> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String str = genres[i];
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + plays[i]);
            } else {
                map.put(str, plays[i]);
            }
        }
        
        for (int i = 0; i < genres.length; i++) {
            list.add(new Music(i, genres[i], map.get(genres[i]), plays[i]));
        }
        
        Collections.sort(list);
        
        Map<String, Integer> cntMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            if (!cntMap.containsKey(list.get(i).genre)) {
                cntMap.put(list.get(i).genre, 1);
                result.add(list.get(i).index);
            } else if (cntMap.get(list.get(i).genre) < 2) {
                cntMap.put(list.get(i).genre, 2);
                result.add(list.get(i).index);
            }
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}
