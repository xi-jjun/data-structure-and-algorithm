#include <string>
#include <vector>
#include <cctype>
#include <set>
#include <algorithm>

using namespace std;

struct Data {
    string city_name;
    int input_seq;
    int hit_cnt;
};

bool cmp(Data d1, Data d2) {
    // if (d1.hit_cnt == d2.hit_cnt) {
    //     return d1.input_seq < d2.input_seq;
    // }
    // return d1.hit_cnt < d2.hit_cnt;
    return d1.input_seq < d2.input_seq;
}

set<string> cached;
Data cache[31];

bool is_cached(string city) {
    return cached.find(city) != cached.end();
}

void reset_seq(string city, int seq) {
    for (int i = 0; i < cached.size(); i++) {
        if (cache[i].city_name == city) {
            cache[i].input_seq = seq;
            return;
        }
    }
}

void erase_LRU(int cacheSize) {
    sort(cache, cache + cacheSize, cmp);
    Data lru_data = cache[0];
    cached.erase(lru_data.city_name);
}

int solution(int cacheSize, vector<string> cities) {
    if (cacheSize == 0) return cities.size() * 5;
    int seq = 0;
    int answer = 0;
    for (string city : cities) {
        transform(city.begin(), city.end(), city.begin(), ::toupper);
        if (is_cached(city)) {
            answer++;
            reset_seq(city, seq);
        } else {
            answer += 5;
            if (cached.size() >= cacheSize) {
                erase_LRU(cacheSize);
                cache[0] = {city, seq, 1};
            } else {
                cache[cached.size()] = {city, seq, 1};
            }
            cached.insert(city);
        }
        seq++;
    }
    return answer;
}