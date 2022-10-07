#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
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
    int test[10] = {2, 10, 5, -5, 3, 44, 50, -80, 1000, 20};
    insert_sort(test, 10);
    for(int i = 0 ; i < 10; i++){
        cout<<test[i]<<" ";
    }
    cout<<endl;
    return 0;
}