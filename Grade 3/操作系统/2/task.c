//Where defined struct task_struct
#include <linux/sched.h>
//where defined initialization and exit
#include <linux/init.h>
//where diefined basical functions and macros
#include <linux/kernel.h>
//where definded function, fieds about kenal module
#include <linux/module.h>
//where definded init_task, which is used to get 0 proccess.
#include <linux/init_task.h>
//where struct page defined
#include <linux/mm_types.h>
//pgd_list and pgd_lock
#include <asm/pgtable.h>
#include <linux/list.h>
//spinlock
//pg_data_t and macro
#include <linux/mmzone.h>
//#include <asm/numa.h>
//#include
#define WRITEBUF 1024
#define READBUF 5120 
//proc
struct proc_dir_entry *proc_dir = NULL;
struct proc_dir_entry *proc_file = NULL;
char write_buf[WRITEBUF];
char read_buf[READBUF];
int pid = 1;
int length = 0;
#include <linux/proc_fs.h>
// add macros handle
struct pglist_data* first_online_pgdat(void){
	return NODE_DATA(first_online_node);
}
struct pglist_data *next_online_pgdat(struct pglist_data *pgdat)
{
	int nid = next_online_node(pgdat->node_id);
	if(nid = MAX_NUMNODES)
		return NULL;
	return NODE_DATA(nid);
}
struct zone* next_zone(struct zone* zone){
	pg_data_t *pgdat = zone->zone_pgdat;
	if (zone < pgdat->node_zones + MAX_NR_ZONES - 1)
		zone++;
	else{
		pgdat = next_online_pgdat(pgdat);
		if(pgdat){
			zone = pgdat->node_zones;
		}
		else{
			zone = NULL;
		}
	}
	return zone;
}
MODULE_DESCRIPTION("procfs:task_struct, page");
MODULE_VERSION("1");
MODULE_LICENSE("GPL");
LIST_HEAD(pgd_list);
DEFINE_SPINLOCK(pgd_lock);
//count how many proccesses
int count_proccess(struct task_struct * zero_T){
	struct list_head *pos;
	int count = 0;
	list_for_each(pos,&zero_T->tasks)
	{
		count++;
	}
	return count;
}
void get_state(long state, char ** result){
	if(state==0){
		*result = "RUNNING";
	}else if(state==1){
		*result = "INTERRUPTIBLE SLEEPING";
	}else if(state==2){
		*result = "UNITERRUPTIBLE SLEEPING";
	}else if(state==64){
		*result = "DEAD";
	}else if(state==128){
		*result = "WAKEKILL";
	}else if(state==256){
		*result = "WAKING";
	}else if(state==512){
		*result = "PARKED";
	}else if(state=1024){
		*result = "NOLOAD";
	}else if(state==2048){
		*result = "NEW";
	}else if(state==4096){
		*result = "STATE_MAX";
	}else if(state==1026){
		*result = "ZOMBIE";
	}else{
		*result = "UNKNOWN";
	}

}
//print numbers of proccess in different states
void print_numbers_state(struct task_struct *t){
	struct list_head *pos;
	struct task_struct *p;
	int zombie = 0;
	int running = 0;
	int interruptible = 0;
	int uninterruptible = 0;
	int dead = 0;
	int wakekill = 0;
	int waking = 0;
	int new = 0;
	int parked = 0;
	int noload = 0;
	int unknown = 0;
	int state_max = 0;
	list_for_each(pos, &t->tasks){
		p = list_entry(pos,struct task_struct,tasks);
		long state = p->__state;
		if(state==0){
			running++;
		}else if(state==1){
			interruptible++;
		}else if(state==2){
			uninterruptible++;
		}else if(state==64){
			dead++;
		}else if(state==128){
			wakekill++;
		}else if(state==256){
			waking++;
		}else if(state==512){
			parked++;
		}else if(state=1024){
			noload++;
		}else if(state==2048){
			new++;
		}else if(state==4096){
			state_max++;
		}else if(state==1026){
			zombie++;
		}else{
			unknown++;
		}
	}
	length += sprintf(read_buf + length,"running:%d zombie:%d interruptible:%d uninterruptible:%d dead:%d new:%d parked:%d state_max:%d wakekill:%d noload:%d waking:%d unknown %d \n", running, zombie, interruptible, uninterruptible, dead, new, parked, state_max, wakekill, noload, waking, unknown);
}
struct page* get_one_page(struct mm_struct *mm, long va){
	pgd_t* pgd;
	p4d_t* p4d;
	pud_t *pud;
	pmd_t *pmd;
	pte_t *pte;
	struct page* page;
	pgd = pgd_offset(mm,va);
	if(!pgd_present(*pgd)){
		return NULL;
	}
	p4d = p4d_offset(pgd, va);
	if (!p4d_present(*p4d)){
		return NULL;
	}
	pud = pud_offset(p4d, va);
	if(!pud_present(*pud)){
		return NULL;
	}
	pmd = pmd_offset(pud,va);
	if(!pmd_present(*pmd)){
		return NULL;
	}
	pte = pte_offset_kernel(pmd, va);
	if(!pte_present(*pte)){
		return NULL;
	}
	page = pte_page(*pte);
	return page;
}
//print all informations of all proccesses(task_struct)
void print_task(struct task_struct* zero_T){
	struct list_head *pos;
	struct task_struct *p;
	list_for_each(pos, &zero_T->tasks){
		char* state_string;
		p = list_entry(pos,struct task_struct, tasks);
		if(p->pid == pid){
			long state = p->__state;
			get_state(state,&state_string);	
			length += sprintf(read_buf + length,"pid:%d state:%s\ prio:%d static prio:%d recent used cpu:%d\n",p->pid, state_string, p->prio,p->static_prio, p->recent_used_cpu);
			length += sprintf(read_buf + length,"Struct pages in this task:\n");
			struct mm_struct *mm = p->mm;
			if(mm){
				struct vm_area_struct* vas = mm->mmap;
				long va;
				int page_count = 0;
				for(va = vas->vm_start; va <= vas->vm_end; va = va + PAGE_SIZE){
					page_count++;
					struct page* p = get_one_page(mm,va);
					if(p){
						length += sprintf(read_buf + length,"%d page:\n",page_count);
						length += sprintf(read_buf + length,"	Flags:%lu\n",p->flags);
						length += sprintf(read_buf + length,"	Start:%lx\n",va);
						length += sprintf(read_buf + length,"	End:%lx\n",va+PAGE_SIZE);
					}
				}
			}else{
				length += sprintf(read_buf + length,"Failed to read pages for this task\n");
			}	
			return;
		}
	}
	length += sprintf(read_buf + length, "No task with such pid\n");
}

ssize_t write_proc(struct file* filp, const char __user * buffer, size_t len, loff_t* offset){
	printk("Call write\n");
	int pid_read;
	int numbers;
	if(copy_from_user(write_buf,buffer,len)){
		return -ENOMEM;
	}
	numbers = sscanf(write_buf,"%d",&pid_read);
	if(numbers!=1){
		return -EFAULT;
	}
	pid = pid_read;
	int new_len = strlen(write_buf);
	*offset = new_len;
	printk("Chosen pid is %d\n", pid);
	return new_len;
}

ssize_t read_proc(struct file* file, char __user* data,size_t count, loff_t *off){
	struct task_struct *task;
	task = &init_task;
	if (*off >0 || count < READBUF)
		return 0;
	printk("Now read begins\n");
	int number = count_proccess(task);
	length += sprintf(read_buf + length,"Numbers of proccesses:%d\n",number);
	print_numbers_state(task);
	print_task(task);
	if (copy_to_user(data,read_buf,length)){
		return -EFAULT;
	}
	*off = length;
	printk("Read finished\n");
	return length;
}
struct proc_ops operations = {
	.proc_read = read_proc,
	.proc_write = write_proc,
};

int __init task_init (void){
	proc_dir = proc_mkdir("lab2", NULL);
	proc_file = proc_create("lab_info",0666,proc_dir,&operations);	
	return 0;
}

void __exit task_exit(void){
	if(proc_file){
		proc_remove(proc_file);
	}
	if(proc_dir){
		proc_remove(proc_dir);
	}
	printk("Finished\n");
}


module_init(task_init);
module_exit(task_exit);
