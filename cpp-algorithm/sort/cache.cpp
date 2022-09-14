// 프로그래머스 카카오 2018 [1차] 캐시
#include <string>
#include <vector>
#include <algorithm>
#include <cctype>

using namespace std;

struct city {
    int seq;
    string name;
    
    city(int _seq, string _name) : seq(_seq), name(_name){};
};

bool comp(city o1, city o2) {
    return o1.seq < o2.seq;
}

int find_city(string city_name, vector<city> cache) {
    int ret = 0;
    for (auto cached_city : cache) {
        if (cached_city.name.compare(city_name) == 0) return ret;
        ret++;
    }
    
    return -1;
}

string make_lower_case(string name) {
    string ret;
    for (auto c : name) ret += tolower(c);
    return ret;
}

int solution(int cacheSize, vector<string> cities) {
    int answer = 0;
    vector<city> cache(cacheSize, city(0, "None"));
    int now_cache_size = 0, seq = 1;
    if (cacheSize == 0) return cities.size() * 5;
    
    for (auto now_city_name : cities) {
        now_city_name = make_lower_case(now_city_name);
        
        if (now_cache_size < cacheSize) {
            int find_idx = find_city(now_city_name, cache);
            if (find_idx == -1) {
                cache[now_cache_size] = city(seq, now_city_name);
                answer += 5;
                now_cache_size++;
            } else { // cache hit
                cache[find_idx].seq = seq;
                answer++;
            }
        } else {
            int find_idx = find_city(now_city_name, cache);
            if (find_idx == -1) {
                sort(cache.begin(), cache.end(), comp);
                cache[0] = city(seq, now_city_name);
                answer += 5;
            } else { // cache hit
                cache[find_idx].seq = seq;
                answer++;
            }
        }
        ++seq;
    }
    return answer;
}