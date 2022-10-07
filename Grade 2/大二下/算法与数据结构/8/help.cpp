#include <algorithm>
#include <iostream>
    using namespace std;
int main()
{
    int n, k;
    int a[100000];
    cin >> n >> k;
    long long total_price = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
        total_price = total_price + a[i];
    }
    sort(a, a + n);
    int tmp = n - k; // Jump tmp index in a[100000]
    while (tmp >= 0)
    {
        total_price = total_price - a[tmp];
        tmp = tmp - k;
    }
    cout << total_price;
}