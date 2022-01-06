import java.util.*;

public class Solution {

    static class Music{
        String genre;
        int play;
        int idx;

        public Music(String genre, int play, int idx) {
            this.genre = genre; this.play = play; this.idx = idx;
        }
    }

    public static int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < genres.length; i++){
            if(map.containsKey(genres[i])){
                map.put(genres[i], map.get(genres[i]) + plays[i]);
            }else{
                map.put(genres[i], plays[i]);
            }
        }

        // 1. 속한 노래가 많이 재생된 장르 먼저 수록
        ArrayList< Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        
//         Collections.sort(list, new Comparator< Map.Entry<String, Integer>>(){
//                 @Override
//                 public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
//                     return o2.getValue().compareTo(o1.getValue());
//                 }
//         });

        // 2. 장르 내 노래 선정
        ArrayList<Music> result = new ArrayList<>();
        for(Map.Entry<String, Integer> e : list){
            String gern = e.getKey();
            ArrayList<Music> second = new ArrayList<>();
            for(int i=0; i < genres.length; i++){
                if(genres[i].equals(gern)){
                    second.add(new Music(gern, plays[i], i));
                }
            }
            Collections.sort(second, new Comparator<Music>(){
                @Override
                public int compare(Music o1, Music o2){
                    if(o1.play > o2.play) return -1;
                    else if(o1.play < o2.play) return 1;
                    else{
                        if(o1.idx > o2.idx){
                            return 1;
                        }else{
                            return -1;
                        }
                    }
                }
            });
            
            if(second.size() > 1){
                result.add(second.get(0));
                result.add(second.get(1));
            }else{
                result.add(second.get(0));
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i).idx;
        }
        
        return answer;
    }
}