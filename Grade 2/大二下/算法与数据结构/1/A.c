#include <stdio.h>
#include <stdbool.h>
#include <math.h>
int main (){
    //read amount of flowers
    int amount;
    printf("Please enter the amount of flowers:\n");
    int singnal1 = scanf("%d",&amount);
    if(amount<1||amount>200000){
        return 0;
    }
    //read all flower
    int readTime  = amount;
    int flowers[amount];
    while(readTime>0){
        printf("Please enter the type of the %d flower:",amount-readTime+1);
        int singnal2 = scanf("%d\n",&(flowers[amount-readTime]));
        printf("\n");
        if(flowers[amount-readTime]<1||flowers[amount-readTime]>pow(10,9)){
            return 0;
        }
        readTime--;
    }
    
    int previousPointer = 0;
    int pointer = 0;
    int previousValue = 0;
    int currentValue = 0;
    int previousLength = 0;
    int currentLength = 0;
    for(int i = 0 ;i < amount; i++){
        currentLength++;
        pointer = i;
        if((currentValue==previousValue)&&(flowers[i]==previousValue)){
            if(currentLength-1>previousLength){
                previousLength = currentLength-1;
                previousPointer = pointer-1;
                i-=2;
                currentLength = 0;
            }
        }
        previousValue = currentValue;
        currentValue = flowers[i];
    }
    int resultEndNumber;
    int resultStartNumber;
    int resultLength;
    if(currentLength<=previousLength){
        resultEndNumber = previousPointer+1;
        resultLength = previousLength;
        resultStartNumber = resultEndNumber - (previousLength-1);
    }else{
        resultEndNumber = pointer+1;
        resultLength = currentLength;
        resultStartNumber = resultEndNumber - (currentLength-1);
    }
    printf("%d %d",resultStartNumber,resultEndNumber);
    return 0;
}