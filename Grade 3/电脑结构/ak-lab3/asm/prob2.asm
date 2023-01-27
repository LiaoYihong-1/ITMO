section .data
limit:4000000
result:2
first:1
temp:0
second:2
section .text
_start:
.loop:
ld second
st temp
add first
st second
div #2
mul #2
cmp second
jnz .go
ld result
add second
st result
.go:
ld temp
st first
ld second
cmp limit
js .loop
ld result
sub second
HLT