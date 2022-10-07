#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
using namespace std;
 
bool compare(string a, string b) {
 
    for (int i = 0; i < a.length() && i < b.length(); i++) {
        if (a[i] > b[i]) return true;
        if (a[i] < b[i]) return false;
    }
 
    if (a.length() < b.length()) return compare(a, b.substr(a.length()));
    if (a.length() > b.length()) return compare(a.substr(b.length()), b);
 
    return true;
}
 
vector<string> quickSort(vector<string> numbers) {
 
    int middle = (numbers.size()-1) / 2;
    vector<string> left, right, mergedNums, midNums;
    if (numbers.size() < 1) {
        return numbers;
    }
 
    for (int i = 0; i < numbers.size(); i++) {
        if (compare(numbers[i], numbers[middle]) && i != middle) {
            left.push_back(numbers[i]);
        } else if (i == middle) {
            midNums.push_back(numbers[i]);
        } else if ((compare(numbers[middle], numbers[i]) && i != middle)) {
            right.push_back(numbers[i]);
        }
    }
 
    left = quickSort(left);
    right = quickSort(right);
 
    for (int i = 0; i < left.size(); i++) {
        mergedNums.push_back(left[i]);
    }
 
    mergedNums.push_back(midNums[0]);
 
    for (int i = 0; i < right.size(); i++) {
        mergedNums.push_back(right[i]);
    }
 
    return mergedNums;
}
 
int main() {
    vector<string> numbers;
    vector<string> sorted;
    string s = "s";
 
 
    while (s!=""&&cin >> s) {
        numbers.push_back(s);
    }
 
    sorted = quickSort(numbers);
 
    for (auto & i : sorted) {
        cout << i;
    }
 
 
}