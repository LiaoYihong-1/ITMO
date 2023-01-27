section .data
a:2520
b:11
arg1:0
arg2:0
temp:0
section .text
_start:;answer is 232792560
.loop:
ld a
st arg1
ld b
st arg2
call find_gcf
st temp
ld a
mul b
div temp
st a
ld b
add #1
st b
cmp #21
jnz .loop
ld a
HLT

find_gcf:
.start:
ld arg1;find mod
div arg2
mul arg2
sub arg1
jz .found
js .inv
jmp .go
.inv:
inv
.go:
st temp
ld arg2
st arg1
ld temp
st arg2
jmp .start
.found:
ld arg2
ret