#include <iostream>
#include <vector>
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

void quick_sort_arr(int *nums, int left, int right)
{
    if (right - left > 12)
    {
        if (left >= right)
            return;
        // random pivot
        int pindex = rand() % (right - left + 1) + left;
        int temp = nums[pindex];
        nums[pindex] = nums[left];
        nums[left] = temp;
        int pivot = nums[left];
        int save_started = left;
        int save_end = right;
        while (left < right)
        {
            while (left < right && nums[right] >= pivot)
            {
                right--;
            }
            if (left < right)
            {
                nums[left] = nums[right];
            }
            while (left < right && nums[left] <= pivot)
            {
                left++;
            }
            if (left < right)
            {
                nums[right] = nums[left];
            }
        }
        nums[left] = pivot;
        quick_sort_arr(nums, save_started, left - 1);
        quick_sort_arr(nums, left + 1, save_end);
    }
    else
    {
        insert_sort(nums + left, right - left + 1);
        return;
    }
}

void merge(int *nums, int left, int mid, int right){
    int n = right - left + 1;
    int *tmp = new int[n];
    int i = 0;
    int record_left = left;
    int record_right = mid + 1;
    while(record_left <= mid && record_right <= right){
        tmp[i++] = nums[record_left] <= nums[record_right]?nums[record_left++]:nums[record_right++];
    }
    while(record_left <= mid){
        tmp[i++] = nums[record_left++];
    }
    while(record_right <= right){
        tmp[i++] = nums[record_right++];
    }
    for(int j = 0 ; j < n; j++){
        nums[left+j] = tmp[j];
    }
    delete [] tmp;
}

void mergesort(int *nums, int left, int right){
    if(left == right){
        return;
    }
    int mid = (right + left)/2;
    mergesort(nums, left, mid);
    mergesort(nums, mid+1, right);
    merge(nums,left,mid,right);
}

int main()
{
    int prices[100000];
    int n;
    int k;
    cin >> n >> k;
    int paid = 0;
    for (int i = 0; i < n; i++)
    {
        int j;
        cin >> j;
        prices[i] = j;
        paid = paid + j;
    }
    mergesort(prices, 0, n - 1);
    // we know that we need to make receipt as more as possible, and in fact gap between the free gift is the same(k)
    // make sure cheapest, should from end to start, if from start to end, example will be 1500
    int free = n - k;
    while (free >= 0)
    {
        paid = paid - prices[free];
        free = free - k;
    }
    cout << paid << endl;
    return 0;
}