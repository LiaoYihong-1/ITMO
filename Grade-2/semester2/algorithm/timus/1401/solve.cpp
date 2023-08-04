#include <iostream>
#include <math.h>
using namespace std;

int num = 1;

int rank_main;
int * square_reader;

void work(int relative_x, int relative_y, int deleted_x, int deleted_y, int rank_relative){
    if(rank_relative == 1) return;

    rank_relative = rank_relative/2;
    int centerx = relative_x + rank_relative;
    int centery = relative_y + rank_relative;
    int current_num = num++;

    //right up
    if( deleted_x < centerx && deleted_y >= centery ){
        work(relative_x, centery, deleted_x, deleted_y, rank_relative);
    }else{
        *(square_reader + (centerx-1)*rank_main + centery) = current_num;
        work(relative_x, centery, centerx-1, centery, rank_relative);
    }

    //left up
    if(deleted_x < centerx && deleted_y < centery){
        work(relative_x, relative_y, deleted_x, deleted_y, rank_relative);
    }else{
        *(square_reader + (centerx-1)*rank_main + centery - 1) = current_num;
        work(relative_x, relative_y, centerx - 1, centery - 1, rank_relative);
    }

    //left down
    if(deleted_x >= centerx && deleted_y < centery ){
        work( centerx , relative_y, deleted_x, deleted_y, rank_relative);
    }else{
        *(square_reader+centerx*rank_main+centery-1) = current_num;
        work( centerx, relative_y, centerx, centery-1, rank_relative); 
    }

    //right down
    if( deleted_x >= centerx && deleted_y >= centery){
        work(centerx, centery, deleted_x , deleted_y , rank_relative);
    }else{
        *(square_reader + centerx*rank_main + centery) = current_num;
        work(centerx, centery, centerx , centery, rank_relative);
    }

}

int main(){
    int square_size;
    int deleted_x;
    int deleted_y;
    string input;
    getline(cin,input);
    square_size = stoi(input);
    rank_main = pow(2,square_size);
    getline(cin,input);
    //split
    int position = input.find(" ");
    deleted_x = stoi(input.substr(0,position));
    deleted_y = stoi(input.substr(position+1,input.size()-position-1));
    //put 0
    int square [rank_main][rank_main];
    square[deleted_x-1][deleted_y-1] = 0;
    square_reader = &square[0][0];
    work(0, 0, deleted_x-1, deleted_y-1, rank_main);
    for(int i = 0 ; i < rank_main ; i++){
        for(int j = 0; j < rank_main ; j++){
            cout<<square[i][j];
            cout<<" ";
        }
        cout<<endl;
    }
    return 0;
}