#include <iostream>
#include <vector>
using namespace std;
vector<int> commands;
struct block{
    struct block* next;
    bool is_free;
    int length;
    int index;//position of start
    int right;
};
int normal_command[100001] = {-1};//help get k-th normal command when free.Start from 1
vector<int> free_command;//help us know which block is freed
vector<struct block> blocks(100001);
vector<struct block> fragments;
int n,m;
int pointer = 0;//plus when add a block to blocks,record the current occupied size
bool first_put = false;//is first blocked is set
void deal_command(int command,int i){
    if(!first_put){
        if(command >= 0 and command <= n){
            struct block b = {.next = NULL,.is_free = false,.length=command,.index=1,.right=n-command};
            blocks[pointer] = b;
            normal_command[i] = pointer;
            pointer++;
            cout<<b.index<<endl;
            first_put = true;
        }else{
            cout<<-1<<endl;
        }
    }else{
        if(command > 0){
            bool find_free = false;
            int block_free_num = 0;
            for(int k = 0; k < free_command.size();k++){
                if(blocks[normal_command[-free_command[k]]].length>=command){
                    find_free =true;
                    block_free_num = normal_command[-free_command[k]];
                    break;
                }
            }
            if(find_free){
                struct block block_free = blocks[block_free_num];
                block_free.is_free = false;
                int new_length = block_free.length - command;
                if(new_length!=0){
                    block_free.length = command;
                    block_free.right = block_free.right + new_length;
                    //插入一个
                    struct block new_block = {.next = block_free.next,.is_free=true,.length = new_length,.index=block_free.index+command,.right=block_free.right-new_length};
                    fragments.emplace_back(new_block);
                }
                blocks[block_free_num] = block_free;
                cout<<block_free.index<<endl;
            }//放在末尾
            else if(blocks[pointer-1].right>=command){
                struct block b = {.next = NULL,.is_free = false,.length=command,.index=blocks[pointer-1].index+blocks[pointer-1].length,.right=n-command};
                struct block last = blocks[pointer-1];
                last.next = &b;
                blocks[pointer-1] = last;
                blocks[pointer] = b;
                normal_command[i] = pointer;
                pointer++;
                cout<<b.index<<endl;
            }else{
                cout<<-1<<endl;
            }
        }else{
            int reverse_command = - command;
            if(reverse_command >= i or normal_command[reverse_command]==-1){
                cout<<-1<<endl;
            }else if( blocks[normal_command[reverse_command]].is_free){
                cout<<-1<<endl;
            }
            else{
                struct block b = blocks[normal_command[reverse_command]];
                b.is_free = true;
                blocks[normal_command[reverse_command]] = b;
                free_command.emplace_back(command);
            }
        }
    }
}

int main(){
    cin>>n>>m;
    int command;
    for(int i = 0 ; i < m;i++){
        cin>>command;
        deal_command(command,i+1);
    }
    return 0;
}