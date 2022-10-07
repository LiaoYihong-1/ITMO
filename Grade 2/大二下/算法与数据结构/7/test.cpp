#include <iostream>
using namespace std;
int main()

{
	int n, k, p,a;
	int sum = 0;
	cin >>n>>k>>p;
	int arr[n+1]={0};
	for(int i=1; i<p+1;i++)
    {
    	cin>>a;
        if(arr[a]==0){
            arr[a]=i;
            sum++;
        }
        else
        {
            if( i - arr[a] > k){
                sum++;
            }
            arr[a] = i;
        }
    }
    cout<<sum<<endl;
    return 0;

}