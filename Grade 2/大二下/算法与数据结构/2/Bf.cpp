#include <iostream>
#include <cstring>
#include <stdbool.h>
using namespace std;
class Counter{
    public:
    char allType[26] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    int balance[26] = {0};
    void resetBalacne(){
        for(int i= 0 ; i < 26 ; i++){
            balance[i] = 0;
        }
    }
    //should be upper
    void addBalance(char c){
        for(int i = 0; i < 26;i++){
            if(allType[i] == c){
                balance[i] = balance[i] + 1;
                return;
            }
        }
    }
    //should be lower
    void minBalance(char c){
        c = toupper(c);
        for(int i = 0; i < 26 ; i++){
            if(allType[i] == c){
                balance[i] = balance[i]-1;
                return;
            }
        }
    }
    bool isBalanceZero(){
        for(int i = 0; i<26; i++){
            if(balance[i] != 0){
                return false;
            }
        }
        return true;
    }
};

int main()
{
    char str1[100000];
    Counter counter;
    int a = 0;
    cin>>str1;
    int inputLength =strlen(str1);
    int animalsIndexInString[inputLength/2];
    for(int i = 0; i < inputLength;i++){
        if(islower(str1[i])){
            animalsIndexInString[a] = i;
            a++;
        }
    }
    int lines [inputLength];
    bool possible = true;
    bool capature[inputLength] = {false};
    int resultIndexSet[inputLength/2] = {0};
    int pointerOfSet = 0;
    for(int i = 0 ; i < inputLength&&possible; i++){
        if(!capature[i]){
            bool end = false;
            counter.resetBalacne();
            if(isupper(str1[i])){//this situation is started a trap
                counter.addBalance(str1[i]);
                for(int j = i+1 ; j < inputLength&&!end; j++){
                    if(!capature[j]){
                        if(isupper(str1[j])){
                            counter.addBalance(str1[j]);
                        }else{
                            counter.minBalance(str1[j]);
                            if(toupper(str1[j])==str1[i]){
                                if(counter.isBalanceZero()){
                                    end=true;//break
                                    lines[i] = j;//i trap
                                    resultIndexSet[pointerOfSet] = i;
                                    pointerOfSet++;
                                    capature[i] = true;
                                    capature[j] = true;
                                }
                            }
                        }
                    }
                }
            }else{//this situation is started at an animal
                counter.minBalance(str1[i]);
                for(int j = i+1; j < inputLength&&!end; j++){
                    if(!capature[j]){
                        if(isupper(str1[j])){
                            counter.addBalance(str1[j]);
                            if(tolower(str1[j])==str1[i]){
                                if(counter.isBalanceZero()){
                                    end = true;//break
                                    lines[j] = i;
                                    resultIndexSet[pointerOfSet] = j;
                                    pointerOfSet++;
                                    capature[i] = true;
                                    capature[j] = true;
                                }
                            }
                        }else{
                            counter.minBalance(str1[j]);
                        }
                    }   
                }
            }
            //if end!=true, means that go to end of all but balance still !=0
            if(!end){
                possible = false;
            }
        }
    }
    if(!possible){
        cout<<"Impossible";
    }else{
        cout<<"Possible"<<endl;
        for(int i = 0; i < inputLength/2-1 ;i++){
            for(int j = i + 1; j < inputLength/2;j++){
                int temp = resultIndexSet[j-1];
                if(resultIndexSet[j-1]>resultIndexSet[j]){
                    resultIndexSet[j-1] = resultIndexSet[j];
                    resultIndexSet[j] = temp;
                }
            }
        }
        for(int i = 0; i < inputLength/2; i++){    
            int num = lines[resultIndexSet[i]];
            for(int j = 0 ; j < inputLength/2 ; j++){
                if(animalsIndexInString[j] == num){
                    cout<<j+1<<" ";
                }
            }
        }        
    }
    cout<<endl;
    return 0;
}