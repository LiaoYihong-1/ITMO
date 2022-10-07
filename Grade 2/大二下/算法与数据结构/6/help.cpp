#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

bool is_greater(string num_1, string num_2) {
 for (int i = 0; i < min(num_1.size(), num_2.size()); i++) {
  if (num_1[i] != num_2[i])
  {
   return num_1[i] > num_2[i];
  }
 }
 if (num_1.size() == num_2.size()) {
  return false;
 }

 return num_1.size() > num_2.size()? is_greater(num_1.substr(num_2.size(), num_1.size()), num_2) :
  is_greater(num_1, num_2.substr(num_1.size(), num_2.size()));

}

int main() {
 vector<string> nums;
 string tmp_string;

 while (cin >> tmp_string) {
  nums.push_back(tmp_string);
 }
 sort(nums.begin(), nums.end(), is_greater);

 for (int i = 0; i< nums.size(); i++)
 {
  cout << nums[i];
 }

}