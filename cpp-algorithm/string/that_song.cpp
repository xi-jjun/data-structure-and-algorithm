#include <string>
#include <vector>
#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

const string patterns[5] = {"C#", "D#", "F#", "G#", "A#"};
const string replaceds[5] = {"c", "d", "f", "g", "a"};

void replace_all(string &m, string pattern, string replaced) {
    int pos = 0, offset = 0;
    while ((pos = m.find(pattern, offset)) != -1) {
        m.replace(m.begin() + pos, m.begin() + pos + pattern.length(), replaced);
        offset = pos + replaced.length();
    }
}

string parsing_m(string m) {
    for (int i = 0; i < 5; i++) {
        replace_all(m, patterns[i], replaceds[i]);
    }
    return m;
}

pair<string, int> get_title(int start_idx, string music_info) {
    string title;
    for (int i = start_idx; i < music_info.length(); i++) {
        if (music_info[i] == ',') {
            return {title, i + 1};
        } else {
            string str(1, music_info[i]);
            title += str;
        }
    }
    return {title, music_info.length()};
}

int get_time(int h1, int h2, int m1, int m2) {
    int hour = abs(h1 - h2);
    int minute = 0;
    if (m1 > m2) {
        --hour;
        minute += (m2 + 60) - m1;
    } else  minute += m2 - m1;
    
    return hour * 60 + minute;
}

string solution(string m, vector<string> musicinfos) {
    string answer = "";
    m = parsing_m(m);
    pair<int, string> ans_candidate = {0, ""};
    
    for (string music_info : musicinfos) {
        int h1, h2, m1, m2, idx = 12;
        string title, music;
        
        h1 = stoi(music_info.substr(0, 2));
        m1 = stoi(music_info.substr(3, 2));
        h2 = stoi(music_info.substr(6, 2));
        m2 = stoi(music_info.substr(9, 2));
        
        pair<string, int> parsed = get_title(idx, music_info);
        title = parsed.first;
        idx = parsed.second;

        music = music_info.substr(idx);
        music = parsing_m(music);
        
        int time = get_time(h1, h2, m1, m2);
        
        if (time >= music.length()) {
            int again_cnt = time / music.length() - 1;
            string again = music;
            while (again_cnt-- > 0) music += again;
            for (int i = 0; i < time % again.length(); i++) music += again[i];
        } else {
            string new_music = "";
            for (int i = 0; i < time; i++) new_music += music[i];
            music = new_music;
        }
        
        bool is_correct = music.find(m) != -1;
        if (is_correct && time > ans_candidate.first) {
            ans_candidate.first = time;
            ans_candidate.second = title;
        }
    }
    
    return ans_candidate.second == "" ? "(None)" : ans_candidate.second;
}