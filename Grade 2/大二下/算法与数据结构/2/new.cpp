#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;
int strlens(const char* strSrc) {
 int len = 0;
 while (*strSrc++ != '\0') len++;
 return len;
}
int main() {
 string result = "Possible";
 string zoo_list;
 stack<pair<char, int>>stk;
 vector<pair<int, int>> answer_arr;
 cin >> zoo_list;
 int k = 1;
 if (islower(zoo_list[0])) {
  stk.emplace(zoo_list[0], 1);
  k++;
 }
 else stk.emplace(zoo_list[0], 0);

 for (int i = 1; i < zoo_list.size(); i++) {
  if (stk.empty()) {
   if (islower(zoo_list[i])) {
    stk.emplace(zoo_list[i], k);
    k++;
   }
   else stk.emplace(zoo_list[i], i);
  }
  else if (zoo_list[i] != stk.top().first && (toupper(stk.top().first) == zoo_list[i] || tolower(stk.top().first) == zoo_list[i])) {
   if (islower(zoo_list[i])) {
    answer_arr.emplace_back(stk.top().second,k);
    k++;
   }
   else {
    answer_arr.emplace_back(i, stk.top().second);
   }
   stk.pop();
  }
  else if (islower(zoo_list[i])) {
    stk.emplace(zoo_list[i], k);
    k++;
   }else stk.emplace(zoo_list[i], i);
  
 }
 if (stk.empty()) {
  cout << "Possible" << endl;
  sort(answer_arr.begin(), answer_arr.end());
  for(auto i:answer_arr)
  {
   cout << i.second << ' ';
  }
 }
 else cout << "Impossible";


 return 0;
}