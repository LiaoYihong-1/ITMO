#include <iostream>
#include <vector>
using namespace std;

void quick_sort_arr(int *nums, int left, int right){
    if(left >= right) return;
    //random pivot
    int pindex = rand()%(right-left+1) + left;
    int temp = nums[pindex];
    nums[pindex] = nums[left];
    nums[left] = temp;
    int pivot = nums[left];
    int save_started = left;
    int save_end = right;
    while(left < right){
        while(left < right && nums[right] >= pivot){
            right--;
        }
        if(left < right){
            nums[left] = nums[right];
        }
        while(left < right && nums[left] < pivot){
            left++;
        }
        if(left < right){
            nums[right] = nums[left];
        }
    }
    nums[left] = pivot;
    quick_sort_arr(nums,save_started, left - 1);
    quick_sort_arr(nums, left+1, save_end);
}

int main(){
    int prices[100000];
    int n;
    int k;
    cin >> n >> k;
    int paid = 0;
    for(int i  = 0;  i < n ; i ++){
        int j;
        cin >> j;
        prices[i] = j;
        paid = paid + j;
    }
    quick_sort_arr(prices, 0 , n - 1);
    //we know that we need to make receipt as more as possible, and in fact gap between the free gift is the same(k)
    //make sure cheapest, should from end to start, if from start to end, example will be 1500
    int free = n - k;
    while(free >= 0){
        paid = paid - prices[free];
        free = free - k;
    }
    cout<<paid<<endl;
    return 0;
}