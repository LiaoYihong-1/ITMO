#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
using namespace std;
void insert_sort(int *arr, int len)
{
    for (int i = 0; i < len; i++)
    {
        int key = arr[i];
        int j = i - 1;
        while ((j >= 0) && (key < arr[j]))
        {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

int main()
{
    int a[] = {1,2,3};
    int b[3];
    copy(begin(a),end(a),b);
    b[2] = 100;
    cout<<a[2]<<" "<<b[2]<<" "<<b[1]<<endl;
    return 0;
}