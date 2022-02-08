package programmers.wooteco;

import java.util.HashMap;
import java.util.Map;

public class Sol3 {
    public static int solution(String[] ings, String[] menu, String[] sell) {
        int answer = 0;
        Map<Character, Integer> ingredientInfo = new HashMap<>(); // ingredient | price
        getIngsPrice(ings, ingredientInfo);

        Map<String, Integer> oneSellList = new HashMap<>(); // name | sell price - original price
        getOneSellPrice(oneSellList, ingredientInfo, menu);

        for (String s : sell) {
            String[] line = s.split(" ");
            String name = line[0];
            int sellCnt = Integer.parseInt(line[1]);

            answer += oneSellList.get(name) * sellCnt;
        }

        return answer;
    }

    private static void getIngsPrice(String[] ings, Map<Character, Integer> ingredientInfo) {
        for (String infos : ings) {
            String[] info = infos.split(" ");
            ingredientInfo.put(info[0].charAt(0), Integer.parseInt(info[1]));
        }
    }

    private static void getOneSellPrice(Map<String, Integer> oneSellList, Map<Character, Integer> ingInfo, String[] menus) {
        for (String menu : menus) {
            String[] line = menu.split(" ");
            String name = line[0];
            String ingredients = line[1];
            int sellPrice = Integer.parseInt(line[2]);
            int originalPrice = getOriginalPrice(ingInfo, ingredients);
            oneSellList.put(name, sellPrice - originalPrice);
        }
    }

    private static int getOriginalPrice(Map<Character, Integer> ingInfo, String ingredients) {
        int originalPrice = 0;
        for (String ingredient : ingredients.split("")) {
            originalPrice += ingInfo.get(ingredient.charAt(0));
        }
        return originalPrice;
    }

    public static void main(String[] args) {
//        String[] ings = {"r 10", "a 23", "t 124", "k 9"}; // 재료비용 테이블. 재료종류 | 비용
//        String[] menu = {"PIZZA arraak 145", "HAMBURGER tkar 180", "BREAD kkk 30",
//                "ICECREAM rar 50", "SHAVEDICE rar 45", "JUICE rra 55", "WATER a 20"}; // 메뉴 정보. 이름 | 필요재료 | 판매가격
//        String[] sell = {"BREAD 5", "ICECREAM 100", "PIZZA 7", "JUICE 10", "WATER 1"}; // 오늘 판매한 실적. 판매이름 | 개수

        String[] ings = {"x 25", "y 20", "z 1000"}; // 재료비용 테이블. 재료종류 | 비용
        String[] menu = {"AAAA xyxy 15", "TTT yy 30", "BBBB xx 30"}; // 메뉴 정보. 이름 | 필요재료 | 판매가격
        String[] sell = {"BBBB 3", "TTT 2"}; // 오늘 판매한 실적. 판매이름 | 개수
        System.out.println(solution(ings, menu, sell));
    }
}
