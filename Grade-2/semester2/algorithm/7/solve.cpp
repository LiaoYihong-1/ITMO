#include <iostream>
#include <vector>
using namespace std;
int w = 100000;
void quick_sort_arr(int *nums, int left, int right){
    if(left >= right) return;
    int pivot = nums[left];
    int save_started = left;
    int save_end = right;
    while(left < right){
        while(left < right && nums[right] < pivot){
            right--;
        }
        if(left < right){
            nums[left] = nums[right];
        }
        while(left < right && nums[left] >= pivot){
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

vector<int> quick_sort_arr_1(vector<int> nums, int left, int right){
    if(left >= right) return nums;
    int pivot = nums[left];
    int save_started = left;
    int save_end = right;
    while(left < right){
        while(left < right && nums[right] < pivot){
            right--;
        }
        if(left < right){
            nums[left] = nums[right];
        }
        while(left < right && nums[left] >= pivot){
            left++;
        }
        if(left < right){
            nums[right] = nums[left];
        }
    }
    nums[left] = pivot;
    nums = quick_sort_arr_1(nums,save_started, left - 1);
    nums = quick_sort_arr_1(nums, left+1, save_end);
    return nums;
}

int main(){
    vector<int> a = {12,3,34,12,44,55,1,43,12,-2,32,11,235, 212,5,8972, 12, 31, 89, 2134, 23, 14, 23, 16, 12, 56};
    a = quick_sort_arr_1(a, 0 , 25);
    for(int i = 0; i<26; i++){
        cout<<a[i]<<" ";
    }
    cout<<endl;
    return 0;
}