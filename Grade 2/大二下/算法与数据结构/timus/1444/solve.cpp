#include <iostream>
#include <vector>
#define _USE_MATH_DEFINES
#include <math.h>
#include <set>
#include <algorithm>
using namespace std;
struct Point{
    int x;
    int y;
    int index;
};
Point points[30000];
bool in_quart(Point a, Point b){
    return (a.x*b.x>=0) && (b.y*a.y >= 0);
}

double my_atan(int y, int x){
    if(y < 0){
        return atan2(y,x) + 2*M_PI;
    }else{
        return atan2(y,x);
    }
}

bool sort_angel_distance(Point a, Point b){
    if(my_atan(a.y,a.x) == my_atan(b.y,b.x) && in_quart(a,b)){
        return a.x*a.x + a.y*a.y < b.x*b.x+b.y*b.y;
    }
    return my_atan(a.y,a.x) < my_atan(b.y,b.x);
}
Point first_point;
int main(){
    int elephpotamus;
    cin>>elephpotamus;
    getchar();
    for(int i = 0 ; i < elephpotamus; i++){
        int x;
        int y;
        cin>>x>>y;
        if(i == 0){
            first_point.x = x;
            first_point.y = y;
            first_point.index = 1;
        }
        points[i].index = i + 1;
        points[i].x = x - first_point.x;
        points[i].y = y - first_point.y;
        getchar();
    }
    sort(points, points + elephpotamus, sort_angel_distance);
    cout<<elephpotamus<<endl;
    for(int i = 0 ; i < elephpotamus; i++){
        //cout<<points[i].index<<" "<<points[i].x<<" "<<points[i].y<<" "<<atan2(points[i].y,points[i].x)<<endl;
        cout<<points[i].index<<endl;
    }
    return 0;
}