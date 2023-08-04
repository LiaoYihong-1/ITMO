section .data
section .text
_start:
.loop:
ld INPUT
cmp #-1
jz .end
ST OUTPUT
jnz .loop
.end:
HLT