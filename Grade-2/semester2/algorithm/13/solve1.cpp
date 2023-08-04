#include <iostream>
#include <vector>
using namespace std;
vector<int> commands;
struct node{
    public:
        bool is_free;
        int length;
        int index;//position of start
        int command_index;
        struct node* next;
};
struct link_list{
    public:
        struct node* head;
        struct node* get_final(){
            struct node* p = head;
            if(p==NULL){
                return NULL;
            }
            while(p->next!=NULL){
                p=p->next;
            }
            return p;
        }
        
        struct node* find_by_index(int index){
            struct node* p = head;
            if(p==NULL){
                return NULL;
            }
            if(p->index == index){
                return p;
            }
            while(p->next !=NULL){
                if(p->index == index){
                    return p;
                }
                p=p->next;
            }
        }

        void remove(int index){
            struct node* a = find_by_index(index);
            if(a==NULL){
                return;
            }
            if(head==a){
                head==NULL;
                return;
            }
            struct node* p = head;
            while(p->next!=a){
                p = p->next;
            }
            p->next = a->next;
        }

        void free_command(int command_index){
            struct node* p = head;
            while(p!=NULL){
                if(p->command_index == command_index){
                    p->is_free = true;
                    while(p->next!=NULL and (p->next)->command_index==command_index){
                        p->is_free = true;
                        p=p->next;
                    }
                }
                p=p->next;
            }
        }

        void merge(int command_index){
            struct node* p = head;
            if(p==NULL){
                return;
            }else if(p->command_index==command_index){
                while((p->next)->command_index==command_index){
                    p->length = p->length + (p->next)->length;
                    p->next = (p->next)->next; 
                }
            }else{
                while(p->next != NULL){
                    if(p->command_index == command_index){
                        while((p->next)->command_index==command_index){
                            p->length = p->length + (p->next)->length;
                            p->next = (p->next)->next; 
                        }
                        return;                        
                    }
                    p = p->next;
                }
            }
        }

        struct node* find_good_node(int length){
            struct node* p = head;
            struct node* result =NULL;
            if(p==NULL){
                return NULL;
            }
            while(p->next!=NULL){
                if(p->is_free and p->length >= length){
                    return p;
                }
                p=p->next;
            }
            return NULL;
        }
        void push_back(struct node new_node){
            struct node* new_pointer = new node;
            *new_pointer = new_node;
            struct node* pointer = get_final();
            if(pointer!=NULL){
                pointer->next = new_pointer;
            }
        }
        struct node* find_node_by_command(int command){
            struct node* p = head;
            if(p==NULL){
                return NULL;
            }
            while(p!=NULL){
                if(p->command_index == command){
                    return p;
                }
                p=p->next;
            }
            return NULL;
        }
        //command_index in order to find the command position to split node into left part and right part
        //only when free will be splited
        struct node* split(int left,int right,int command_index){
            struct node* p = head;
            if(p==NULL){
                return NULL;
            }
            //find first command_index
            while(p!=NULL and p->command_index != command_index or !p->is_free){
                p = p->next;
            }
            if(p==NULL){
                return NULL;
            }else{
                struct node right_node = *p;
                right_node.length = right;
                right_node.index = right_node.index + left;
                struct node* right_node_pointer = new node;
                *right_node_pointer = right_node;
                struct node left_node = *p;
                left_node.length = left;
                left_node.next = right_node_pointer;
                *p = left_node;
                return p;
            }            
        }
} link;

void print_node(struct node n){
    cout<<"Command index: "<<n.command_index<<endl;
    cout<<"Index: "<<n.index<<endl;
    cout<<"Length: "<<n.length<<endl;
    cout<<"Free: "<<n.is_free<<endl;
}

int n,m;

int main(){
    //n - length, m - commands
    cin>>n>>m;
    int command;
    bool init = false;
    for(int i = 0 ; i < m;i++){
        cin>>command;
        if(!init){
            if(command>=0){
                init = true;
                struct node start = {.is_free=false,.length=command,.index=1,.command_index=i+1,.next=NULL};
                struct node* start_p = new node;
                *start_p = start; 
                link.head = start_p;
                cout<<start.index<<endl;
            }else{
                cout<<-1<<endl;
            }
        }
        else{
            struct node* final = link.get_final();
            if(command>=0){
                struct node* find_good = link.find_good_node(command);
                if(find_good != NULL){
                    //需要打碎
                    if(find_good->length > command){
                        link.split(command,find_good->length-command,find_good->command_index);
                    }
                    find_good->is_free = false;
                    cout<<find_good->index<<endl;
                }
                else if(command <= n - (*final).index - (*final).length + 1){
                    struct node n_d = {.is_free=false,.length=command,.index=(*final).index+(*final).length,.command_index=i+1,.next=NULL};
                    struct node* new_p = new node;
                    *new_p = n_d;
                    final->next = new_p;
                    cout<<new_p->index<<endl;
                }else{
                    cout<<-1<<endl;
                }
                print_node(*(link.head));
                if((link.head)->next!=NULL){
                    print_node(*(link.head->next));
                }
                if(((link.head)->next)->next!=NULL){
                    print_node(*((link.head->next)->next));
                }
            }else{
                struct node* found = link.find_node_by_command(-command);
                if(-command > final->command_index){
                    cout<<-1<<endl;
                }else if(found == NULL){
                    cout<<-1<<endl;
                }
                else if(!(found->is_free)){
                    link.merge(-command);
                    found->is_free = true;
                }else{
                    cout<<-1<<endl;
                }
                print_node(*(link.head));
            }
        }
    }
    return 0;
}