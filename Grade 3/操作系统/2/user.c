#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
bool is_int(char argv[]){
	
}
int main(int argc, char *argv[]){
	if(argc != 2){
		fprintf(stderr,"This progromnma only accept one parameter\n");
		return 1;
	}
	char* in = argv[1];
	char* error;
	long int pid = strtol(in,&error,10);
	if(*error != '\0')
	{
		fprintf(stderr, "Please only input number cause pid can only be a number\n");
		return 1;
	}
	if(pid  < 0){
		fprintf(stderr,"Pid should be bigger than 0\n");
	}
	char input_buf[1024];
	char result_buf[5120];
	int fd = open("/proc/lab2/lab_info",O_RDWR);
	//int fd = open("./test.txt",O_RDWR);
	write(fd,argv[1],strlen(argv[1]));
	close(fd);
	int new_fd = open("/proc/lab2/lab_info", O_RDWR);
	read(new_fd,result_buf,5120);
	puts(result_buf);
	return 0;
}
