E. Коровы в стойла
#include <iostream>
using namespace std;
int main()
{
 int num_stalls, num_cows;
 cin >> num_stalls >> num_cows;
 int answer = 0;

 int *coord = new int[num_stalls];
 for (int i = 0; i < num_stalls; ++i)
 {
  cin >> coord[i];
 }
 int left_flag = 0, right_flag = coord[num_stalls - 1] - coord[0] + 1;
 while (right_flag - left_flag >1)
 {
  int mid_flag = (left_flag + right_flag) / 2;
  int cows = 1;
  int left_cow = coord[0];
  for (int i = 0;i < num_stalls;i++)
  {
   if (coord[i] - left_cow >= mid_flag)
   {
    cows = cows + 1;
    left_cow = coord[i];
   }
  }

  if (cows >= num_cows)
  {
   left_flag = mid_flag;
  }else
  {
   right_flag = mid_flag;
  }
 }

 cout << left_flag;
 
}