#include <algorithm>
#include <iostream>
#include <tuple>
#include <vector>
using namespace std;
int main()
{
    string string_from_god, head, daeh; // head and daeh hhh
    cin >> string_from_god;
    int char_list[26] = {0};
    vector<pair<long long, int>> costs;
    for (int i = 0; i < 26; ++i)
    {
        long long cost;
        cin >> cost;
        costs.emplace_back(cost, i);
    }
    for (auto c : string_from_god)
    {
        char_list[c - 'a'] = char_list[c - 'a'] + 1;
    }
    sort(costs.begin(), costs.end(), greater<pair<long long, int>>());
    for (auto cost : costs)
    {
        long long value;
        int char_god;
        tie(value, char_god) = cost;
        if (char_list[char_god] < 2)
        {
            continue;
        }
        else
        {
            char_list[char_god] -= 2;
            head = head + char(char_god + 'a');
            daeh = daeh + char(char_god + 'a');
        }
    }
    reverse(daeh.begin(), daeh.end()); // make head become daeh
    for (int i = 0; i < 26; ++i)
        while (char_list[i])
        {
            head += char(i + 'a');
            char_list[i]--;
        }
    cout << head + daeh;
}