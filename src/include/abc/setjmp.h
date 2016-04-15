/* setjmp.h: The ABC representation of standard C library setjmp.
 * Nonlocal jumps <setjmp.h> 7.13.
 * Based on C11 Standard.
 */
#ifndef __SETJMP__
#define __SETJMP__

typedef int jmp_buf[2];

/* Functions */
int setjmp(jmp_buf);
void longjmp(jmp_buf, int);

#endif
